package org.quintilis.common.config

import org.quintilis.common.inteceptor.DynamicPermissionInterceptor
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
class WebInterceptorMvcConfig(
    private val dynamicPermissionInterceptor: DynamicPermissionInterceptor
): WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(dynamicPermissionInterceptor)
            .addPathPatterns("/**") // Aplica em todas as rotas
            .excludePathPatterns("/error", "/static/**", "/assets/**")
    }
}