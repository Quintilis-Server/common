package org.quintilis.common.dto.auth

import org.quintilis.common.dto.BaseDTO
import org.quintilis.common.entities.auth.EndpointRule
import java.util.UUID

data class EndpointRuleDTO(
    val id: UUID,
    val httpMethod: String,
    val urlPattern: String,
    val serviceName: String,
    val permissions: Set<PermissionDTO>,
    val description: String?
): BaseDTO<EndpointRule> {
    override fun toEntity(): EndpointRule {
        return EndpointRule().apply {
            id = this@EndpointRuleDTO.id
            serviceName = this@EndpointRuleDTO.serviceName
            httpMethod = this@EndpointRuleDTO.httpMethod
            urlPattern = this@EndpointRuleDTO.urlPattern
            permissions = this@EndpointRuleDTO.permissions.map { it.toEntity() }.toMutableSet()
            description = this@EndpointRuleDTO.description
        }
    }
}
