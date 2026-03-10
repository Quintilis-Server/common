package org.quintilis.common.repositories

import java.util.UUID
import org.quintilis.common.entities.auth.User
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository

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
