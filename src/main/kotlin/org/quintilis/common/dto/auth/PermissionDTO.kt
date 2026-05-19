package org.quintilis.common.dto.auth

import com.fasterxml.jackson.annotation.JsonCreator
import org.quintilis.common.dto.BaseDTO
import org.quintilis.common.entities.auth.Permission

data class PermissionDTO(
//    val id: Int,
    val name: String,
    val description: String,
): BaseDTO<Permission, Int>() {
    override fun toEntity(): Permission {
        return Permission().apply {
            id = this@PermissionDTO.id
            name = this@PermissionDTO.name
            description = this@PermissionDTO.description
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as PermissionDTO
        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: super.hashCode()
    }

    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun fromString(id: String): PermissionDTO {
            // O Jackson vai pegar o "9", converter pra Int, e criar um DTO!
            return PermissionDTO("", "").apply { this.id = id.toInt() }
        }
    }
}