package org.quintilis.common.dto.auth

import java.time.Instant
import org.quintilis.common.dto.BaseDTO
import org.quintilis.common.entities.auth.Role

data class RoleDTO(
        val id: Int,
        val name: String,
        val displayName: String,
        val color: String,
        val icon: String,
        val priority: Int,
        val createdAt: Instant,
        val permissions: List<String> = emptyList()
) : BaseDTO<Role> {
    override fun toEntity(): Role {
        return Role().apply {
            id = this@RoleDTO.id
            name = this@RoleDTO.name
            displayName = this@RoleDTO.displayName
            color = this@RoleDTO.color
            icon = this@RoleDTO.icon
            priority = this@RoleDTO.priority
            createdAt = this@RoleDTO.createdAt
        }
    }
}
