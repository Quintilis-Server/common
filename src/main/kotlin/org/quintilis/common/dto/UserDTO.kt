package org.quintilis.common.dto

import org.quintilis.common.entities.auth.User
import java.time.Instant
import java.util.UUID

data class UserDTO(
    val id: UUID? = null,
    val username: String,
    val email: String, // Cuidado: Só retorne isso se for o próprio usuário ou admin
    val role: String,
    val avatarPath: String? = null,
    val isVerified: Boolean = false,
    val createdAt: Instant? = null
): BaseDTO<User> {
    
    override fun toEntity(): User {
        return User().apply {
            this.id = this@UserDTO.id
            this.username = this@UserDTO.username
            this.email = this@UserDTO.email
            this.role = this@UserDTO.role
            this.avatarPath = this@UserDTO.avatarPath
            this.isVerified = this@UserDTO.isVerified
            this.createdAt = this@UserDTO.createdAt
            // Senha e IDs sociais não são setados aqui por segurança
        }
    }
}
