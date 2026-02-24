package org.quintilis.common.dto.auth

import java.time.Instant
import java.util.UUID
import org.quintilis.common.dto.BaseDTO
import org.quintilis.common.entities.auth.User

data class UserDTO(
        val id: UUID? = null,
        val username: String,
        val email: String, // Cuidado: Só retorne isso se for o próprio usuário ou admin
        val roles: List<RoleDTO> = emptyList(),
        val avatarPath: String? = null,
        val isVerified: Boolean = false,
        val createdAt: Instant? = null
) : BaseDTO<User> {

    override fun toEntity(): User {
        return User().apply {
            this.id = this@UserDTO.id
            this.username = this@UserDTO.username
            this.email = this@UserDTO.email
            this.avatarPath = this@UserDTO.avatarPath
            this.isVerified = this@UserDTO.isVerified
            this.createdAt = this@UserDTO.createdAt
            // Roles são gerenciados separadamente via user_roles
        }
    }
}
