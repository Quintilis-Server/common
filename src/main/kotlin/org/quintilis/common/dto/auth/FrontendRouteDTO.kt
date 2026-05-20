package org.quintilis.common.dto.auth

import org.quintilis.common.dto.BaseDTO
import org.quintilis.common.entities.auth.FrontendRoute
import java.util.UUID

data class FrontendRouteDTO(
    val path: String,
    val description: String?,
    val permissions: Set<PermissionDTO> = emptySet()
) : BaseDTO<FrontendRoute, UUID>() {
    override fun toEntity(): FrontendRoute {
        return FrontendRoute().apply {
            this.id = this@FrontendRouteDTO.id
            this.path = this@FrontendRouteDTO.path
            this.description = this@FrontendRouteDTO.description
            this.createdAt = this@FrontendRouteDTO.createdAt
            // Permissions are managed separately
        }
    }
}
