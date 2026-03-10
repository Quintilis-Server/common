package org.quintilis.common.config

import org.quintilis.common.entities.auth.EndpointRule
import org.quintilis.common.repositories.EndpointRuleRepository
import org.quintilis.common.repositories.PermissionRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

@Component
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
class EndpointMapperConfig(
    @Qualifier("requestMappingHandlerMapping")
    private val handlerMapping: RequestMappingHandlerMapping,
    private val endpointRuleRepository: EndpointRuleRepository,
    private val permissionRepository: PermissionRepository,

    @Value("\${spring.application.name:default-service}")
    private val appName: String
): ApplicationRunner {
    override fun run(args: ApplicationArguments) {

        println("🚀 Iniciando Sincronização de Endpoints para o serviço: [$appName]")

        val map = handlerMapping.handlerMethods

        // Vamos guardar as assinaturas (Método + URL) de tudo que acharmos no código atual
        val activeEndpointsInCode = mutableSetOf<String>()

        // 1. MAPEIA O CÓDIGO E SALVA/REATIVA NO BANCO
        map.forEach { (requestMappingInfo, _) ->
            val patterns = requestMappingInfo.patternValues
            val methods = requestMappingInfo.methodsCondition.methods

            for(pattern in patterns){
                if(pattern.startsWith("/error") || pattern.startsWith("/swagger") || pattern.startsWith("/v3/api-docs")){
                    continue
                }

                for(requestMethod in methods){
                    val httpMethod = requestMethod.name

                    // Registra na nossa lista de rotas "vivas"
                    activeEndpointsInCode.add("$httpMethod|$pattern")

                    // Tenta achar a regra no banco de dados para ESTE serviço
                    var rule = endpointRuleRepository.findByHttpMethodAndUrlPatternAndServiceName(httpMethod, pattern, appName)

                    if (rule == null) {
                        // É uma rota NOVA!
                        rule = EndpointRule().apply {
                            this.httpMethod = httpMethod
                            this.urlPattern = pattern
                            this.serviceName = appName
                            this.permissions = mutableSetOf()
                        }

                        if(httpMethod in listOf("POST", "PUT", "DELETE", "PATCH")){
                            val defaultPermissions = permissionRepository.findByName("user.change_routes")
                            if(defaultPermissions != null){
                                rule.permissions.addAll(mutableSetOf(defaultPermissions))
                            } else {
                                println("⚠️ Permissão 'user.change_routes' não encontrada no banco. Rota $pattern salva como pública.")
                            }
                        }
                        endpointRuleRepository.save(rule)
                        val permNames = rule.permissions.map { it.name }
                        println("✅ Novo Endpoint mapeado: [$httpMethod] $pattern -> Exige: ${permNames.ifEmpty { "PÚBLICO" }}")

                    } else if (!rule.isActive) {
                        // A rota já existia no banco, estava desativada, mas voltou pro código!
                        rule.isActive = true
                        endpointRuleRepository.save(rule)
                        println("♻️ Endpoint reativado: [$httpMethod] $pattern")
                    }
                }
            }
        }

        // 2. FAZ A FAXINA (SOFT DELETE NO QUE SUMIU)
        // Puxa do banco todas as regras registradas para este serviço
        val dbEndpoints = endpointRuleRepository.findByServiceName(appName)
        var deactivatedCount = 0

        for (dbRule in dbEndpoints) {
            val signature = "${dbRule.httpMethod}|${dbRule.urlPattern}"

            // Se a regra do banco não foi encontrada no código e ainda está ativa, nós desativamos
            if (!activeEndpointsInCode.contains(signature) && dbRule.isActive) {
                dbRule.isActive = false
                endpointRuleRepository.save(dbRule)
                println("🗑️ Endpoint desativado (não existe mais no código): [${dbRule.httpMethod}] ${dbRule.urlPattern}")
                deactivatedCount++
            }
        }

        println("✅ Sincronização finalizada. $deactivatedCount endpoints obsoletos desativados.")
    }
}