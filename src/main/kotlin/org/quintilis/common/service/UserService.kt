package org.quintilis.common.service

import org.quintilis.common.entities.auth.User
import org.quintilis.common.repositories.UserRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@Service
class UserService(private val userRepository: UserRepository) {

    @Cacheable("user", key = "#id")
    fun findById(id: UUID): User? {
        return userRepository.findById(id).getOrNull()
    }
}