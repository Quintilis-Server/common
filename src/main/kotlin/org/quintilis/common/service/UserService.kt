package org.quintilis.common.service

import org.quintilis.common.dto.auth.UserDTO
import org.quintilis.common.entities.auth.User
import org.quintilis.common.repositories.UserRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.reflect.KProperty1

@Service
class UserService(
    private val userRepository: UserRepository
): BaseService<User, UUID, UserDTO, UserDTO>(userRepository), UserRepository by userRepository {
    override fun newDTOToEntity(newDTO: UserDTO): User {
        throw UnsupportedOperationException("A criação de usuários via API genérica não é permitida. Use o fluxo de Registro.")
    }

    override fun getSearchFields(): List<KProperty1<User, *>> {
        return listOf(
            User::username,
            User::email
        )
    }

    @CacheEvict("user", allEntries = true)
    override fun updateEntityFromDTO(
        dto: UserDTO,
        entity: User
    ) {
        entity.username = dto.username
        entity.roles = dto.roles.map { it.toEntity() }.toMutableSet()
        entity.avatarPath = dto.avatarPath
        entity.isVerified = dto.isVerified
    }

    @Cacheable("user", key = "#id")
    override fun findById(id: UUID, includeInactive: Boolean): User {
        return super.findById(id, includeInactive)
    }
}