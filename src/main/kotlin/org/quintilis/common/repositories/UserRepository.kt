package org.quintilis.common.repositories

import org.quintilis.common.entities.auth.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository: JpaRepository<User, UUID> {
    fun findByGoogleId(googleId: String): User?
    fun findByMicrosoftId(microsoftId: String): User?
    fun findByEmail(email: String): User?
}