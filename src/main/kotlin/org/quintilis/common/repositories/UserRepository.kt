package org.quintilis.common.repositories

import java.util.UUID
import org.quintilis.common.entities.auth.User
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, UUID> {
    @Cacheable("users_google", key = "#p0") fun findByGoogleId(googleId: String): User?

    @Cacheable("users_microsoft", key = "#p0") fun findByMicrosoftId(microsoftId: String): User?

    @Cacheable("users_email", key = "#p0", unless = "#result == null")
    fun findByEmail(email: String): User?

    @Cacheable("users_username", key = "#p0", unless = "#result == null")
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
