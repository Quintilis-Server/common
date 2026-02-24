package org.quintilis.common.dto.auth

import org.quintilis.common.dto.BaseDTO
import org.quintilis.common.entities.auth.Permission

data class PermissionDTO(
    val id: Int,
    val name: String,
    val description: String,
): BaseDTO<Permission> {
    override fun toEntity(): Permission {
        return Permission().apply {
            id = this@PermissionDTO.id
            name = this@PermissionDTO.name
            description = this@PermissionDTO.description
        }
    }
}