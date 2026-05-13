package org.quintilis.common.service.minecraft

import jakarta.transaction.Transactional
import org.quintilis.common.dto.minecraft.MineEventDTO
import org.quintilis.common.entities.minecraft.MineEvent
import org.quintilis.common.repositories.BaseRepository
import org.quintilis.common.repositories.minecraft.MineEventRepository
import org.quintilis.common.service.BaseService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.Instant
import kotlin.reflect.KProperty1

@Service
class MineEventService(
    private val repository: MineEventRepository,
) : BaseService<MineEvent, Int, MineEventDTO, MineEventDTO>(repository){
    override fun newDTOToEntity(newDTO: MineEventDTO): MineEvent {
        return MineEvent().apply {
            this.name = newDTO.name
            this.description = newDTO.description
            this.startsAt = newDTO.startsAt
            this.endsAt = newDTO.endsAt
            this.visible = newDTO.visible
            this.createdAt = Instant.now()
        }
    }

    override fun getSearchFields(): List<KProperty1<MineEvent, *>> {
        return listOf(
            MineEvent::name
        )
    }

    @Cacheable("event", key = "#id")
    override fun findById(id: Int, includeInactive: Boolean): MineEvent {
        return super.findById(id, includeInactive)
    }

    @Transactional
    @CacheEvict("event", allEntries = true)
    override fun create(dto: MineEventDTO): MineEventDTO {
        return super.create(dto)
    }

    @Transactional
    @CacheEvict("event", allEntries = true)
    override fun update(dto: MineEventDTO, id: Int): MineEventDTO {
        return super.update(dto, id)
    }

    @Transactional
    @CacheEvict("event", allEntries = true)
    override fun delete(id: Int, hardDelete: Boolean): MineEventDTO {
        return super.delete(id, hardDelete)
    }

    @CacheEvict("event", allEntries = true)
    override fun updateEntityFromDTO(
        dto: MineEventDTO,
        entity: MineEvent
    ) {
        entity.name = dto.name
        entity.description = dto.description
        entity.startsAt = dto.startsAt
        entity.endsAt = dto.endsAt
        entity.visible = dto.visible
    }
}