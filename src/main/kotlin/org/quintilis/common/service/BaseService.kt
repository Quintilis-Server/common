package org.quintilis.common.service

import jakarta.transaction.Transactional
import org.quintilis.common.dto.BaseDTO
import org.quintilis.common.entities.BaseEntity
import org.quintilis.common.exception.NotFoundException
import org.quintilis.common.repositories.BaseRepository
import org.quintilis.common.specifications.SearchSpecification
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KProperty1

abstract class BaseService<E, ID : Any, DTO, NewDTO: Any>(
    private val repository: BaseRepository<E, ID>
) where E : BaseEntity<DTO>, DTO : BaseDTO<E>, DTO : Any{

    protected abstract fun newDTOToEntity(newDTO: NewDTO): E

    protected abstract fun getSearchFields(): List<KProperty1<E, *>>

    protected abstract fun updateEntityFromDTO(dto: DTO, entity: E)

    open fun findAll(search: String?, pageable: Pageable, includeInactive: Boolean = false): Page<DTO> {
        val fieldNames = getSearchFields().map { it.name }

        var spec = SearchSpecification.searchInFields<E>(search, fieldNames)
            ?: Specification<E> { _, _, cb -> cb.conjunction() }

        if (!includeInactive) {
            val activeSpec = Specification<E> { root, _, cb ->
                cb.isTrue(root.get<Boolean>("isActive"))
            }
            spec = spec.and(activeSpec)
        }

        val page = repository.findAll(spec, pageable)
        return page.map { it.toDTO() }
    }

    open fun findById(id: ID, includeInactive: Boolean = false): E {
        val entity = repository.findById(id).orElseThrow { NotFoundException("Entity not found") }

        if (!includeInactive && !entity.isActive) {
            throw NotFoundException("Entity not found")
        }

        return entity
    }

    @Transactional
    open fun update(dto: DTO, id: ID): DTO {
        val entity = repository.findById(id).orElseThrow { NotFoundException("Entity not found") }
        updateEntityFromDTO(dto, entity)
        return repository.save(entity).toDTO()
    }

    @Transactional
    open fun create(dto: NewDTO): DTO {
        val newEntity = newDTOToEntity(dto)
        val savedEntity = repository.save(newEntity)
        return savedEntity.toDTO()
    }

    @Transactional
    open fun delete(id: ID, hardDelete: Boolean = false): DTO {
        val entity = repository.findById(id).orElseThrow { NotFoundException("Entity not found") }

        if (hardDelete) {
            // HARD DELETE: Apaga definitivamente do banco de dados
            repository.delete(entity)
        } else {
            // SOFT DELETE: Apenas marca como inativo e salva
            entity.isActive = false
            repository.save(entity)
        }

        return entity.toDTO()
    }
}