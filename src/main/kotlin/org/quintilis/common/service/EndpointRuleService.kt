package org.quintilis.common.service

import org.quintilis.common.dto.auth.EndpointRuleDTO
import org.quintilis.common.dto.auth.RoleDTO
import org.quintilis.common.entities.auth.EndpointRule
import org.quintilis.common.entities.auth.Permission
import org.quintilis.common.entities.auth.Role
import org.quintilis.common.exception.NotFoundException
import org.quintilis.common.repositories.EndpointRuleRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.util.AntPathMatcher
import java.util.UUID
import kotlin.reflect.KProperty1


@Service
class EndpointRuleService(
    private val endpointRuleRepository: EndpointRuleRepository
): BaseService<EndpointRule, UUID, EndpointRuleDTO, EndpointRuleDTO>(repository = endpointRuleRepository) {

    private val ANT_PATH_MATCHER = AntPathMatcher()

    @Cacheable("endpoint_rules_cache", key = "#currentMethod + '_' + #currentUrl")
    fun getRequiredPermissionFor(currentMethod: String, currentUrl: String): Set<Permission>{
//    println("🔎 Buscando regras para: [$currentMethod] $currentUrl")
//        println(currentMethod)
        val rules = this.getAll()

        for(rule in rules){
            val pattern = rule.urlPattern ?: ""
            val method = rule.httpMethod ?: ""

            val isMatch = ANT_PATH_MATCHER.match(pattern, currentUrl)

            // 🔥 Descomente a linha abaixo se quiser ver TUDO que ele está comparando
            // println("  -> Comparando com: [$method] $pattern | Match URL? $isMatch")

            if(isMatch && method == currentMethod) {
//                println("✅ MATCH ENCONTRADO! Permissões exigidas: ${rule.permissions.map { it.name }}")
                return rule.permissions
            }
        }
        println("⚠️ Nenhum match encontrado. Retornando vazio (Acesso Livre).")
        return emptySet()
    }

    @Cacheable("endpoint_rules")
    fun getAll(): List<EndpointRule>{
        return endpointRuleRepository.findAllLeftJoin()
    }

    @Cacheable("endpoint_rule", key = "#id")
    override fun findById(id: UUID, includeInactive: Boolean): EndpointRule {
        return super.findById(id, includeInactive)
    }

    @CacheEvict("endpoint_rules", "endpoint_rule", "endpoint_rules_cache", allEntries = true)
    override fun update(dto: EndpointRuleDTO, id: UUID): EndpointRuleDTO {
        return super.update(dto, id)
    }

    override fun newDTOToEntity(newDTO: EndpointRuleDTO): EndpointRule {
        return EndpointRule().apply {
            this.httpMethod = newDTO.httpMethod
            this.urlPattern = newDTO.urlPattern
            this.permissions = newDTO.permissions.map { it.toEntity() }.toMutableSet()
        }
    }

    override fun getSearchFields(): List<KProperty1<EndpointRule, *>> {
        return listOf(
            EndpointRule::httpMethod,
            EndpointRule::urlPattern
        )
    }

    override fun updateEntityFromDTO(
        dto: EndpointRuleDTO,
        entity: EndpointRule
    ) {
//        entity.httpMethod = dto.httpMethod
//        entity.urlPattern = dto.urlPattern
        entity.permissions = dto.permissions.map { it.toEntity() }.toMutableSet()
        entity.description = dto.description
    }
}