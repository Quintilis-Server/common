package org.quintilis.common.repositories.auth

import java.util.UUID
import org.quintilis.common.entities.auth.User
import org.quintilis.common.repositories.BaseRepository
import org.springframework.cache.annotation.CacheEvict

interface UserRepository : BaseRepository<User, UUID> {
    fun findByGoogleId(googleId: String): User?

    fun findByMicrosoftId(microsoftId: String): User?

    fun findByEmail(email: String): User?

    fun findByUsername(username: String): User?

    @CacheEvict(
            value = ["users_google", "users_microsoft", "users_email", "users_username"],
            allEntries = true
    )
    override fun <S : User> save(entity: S): S

    @CacheEvict(
            value = ["users_google", "users_microsoft", "users_email", "users_username"],
            allEntries = true
    )
    override fun deleteById(id: UUID)
}
