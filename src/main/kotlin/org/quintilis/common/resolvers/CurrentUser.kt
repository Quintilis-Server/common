package org.quintilis.common.resolvers

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class CurrentUser(
    val requiredPermission: String = ""
)
