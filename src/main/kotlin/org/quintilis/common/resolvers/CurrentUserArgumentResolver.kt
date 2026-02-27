package org.quintilis.common.resolvers

import java.util.UUID
import org.quintilis.common.entities.auth.User
import org.quintilis.common.exception.UnauthorizedException
import org.quintilis.common.repositories.UserRepository
import org.quintilis.common.service.UserService
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class CurrentUserArgumentResolver(private val userService: UserService) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.getParameterAnnotation(CurrentUser::class.java) != null &&
                parameter.parameterType == User::class.java
    }

    override fun resolveArgument(
            parameter: MethodParameter,
            mavContainer: ModelAndViewContainer?,
            webRequest: NativeWebRequest,
            binderFactory: WebDataBinderFactory?
    ): Any? {
        val userIdHeader = webRequest.getHeader("X-User-Id")

        if (userIdHeader.isNullOrBlank()) {
            return null
        }

        val userId = UUID.fromString(userIdHeader)
        val user = userService.findById(userId)
            ?: throw UnauthorizedException("User not found")

        val annotation = parameter.getParameterAnnotation(CurrentUser::class.java)

        if(annotation != null && annotation.requiredPermission.isNotEmpty()){
            if(!user.hasPermission(annotation.requiredPermission)) {
                throw UnauthorizedException("User does not have permission to access this resource")
            }
        }
        return user

//        val rolesHeader = webRequest.getHeader("X-User-Roles") ?: ""
//        val permissionsHeader = webRequest.getHeader("X-User-Permissions") ?: ""
//
//        val userRoles = rolesHeader.split(",").map { it.trim() }.filter { it.isNotEmpty() }.toList()
//
//        return UserSummaryDTO(
//                id = UUID.fromString(userIdHeader),
//                username =
//                        "", // The name is irrelevant since it's just meant for identification when
//                // writing downstream
//                roles = userRoles, // Includes regular roles, but in this object context it doesn't
//                // really matter as the gateway handles auth
//                avatarPath = null,
//                isVerified = true
//        )
    }
}
