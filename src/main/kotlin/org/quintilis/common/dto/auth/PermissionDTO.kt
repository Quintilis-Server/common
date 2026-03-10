package org.quintilis.common.dto.auth

import com.fasterxml.jackson.annotation.JsonCreator
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
    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun fromString(id: String): PermissionDTO {
            // O Jackson vai pegar o "9", converter pra Int, e criar um DTO!
            return PermissionDTO(id = id.toInt(), "", "")
        }
    }
}