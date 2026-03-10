package org.quintilis.common.inteceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.quintilis.common.exception.UnauthorizedException
import org.quintilis.common.service.EndpointRuleService
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

@Component
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
class DynamicPermissionInterceptor(
    private val endpointRuleService: EndpointRuleService
): HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) return true

        val currentUrl = request.requestURI
        val currentMethod = request.method

        val requiredPermissions = endpointRuleService.getRequiredPermissionFor(currentMethod, currentUrl).map {it.name}

        if(requiredPermissions.isNotEmpty()){
            val permissionHeader = request.getHeader("X-User-Permissions") ?: ""
            val userPermissions = permissionHeader.split(",").map { it.trim() }

            val hasAccess = requiredPermissions.any { requiredPerm ->
                userPermissions.contains(requiredPerm)
            }
            if(!hasAccess) {
                throw UnauthorizedException("Access denied. Required permissions: ${requiredPermissions.joinToString(", ")}")
            }
        }

        return true
    }
}