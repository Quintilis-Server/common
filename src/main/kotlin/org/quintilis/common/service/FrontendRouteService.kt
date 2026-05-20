package org.quintilis.common.service

import org.quintilis.common.dto.auth.FrontendRouteDTO
import org.quintilis.common.entities.auth.FrontendRoute
import org.quintilis.common.repositories.auth.FrontendRouteRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.reflect.KProperty1

@Service
class FrontendRouteService(
    private val frontendRouteRepository: FrontendRouteRepository
) : BaseService<FrontendRoute, UUID, FrontendRouteDTO, FrontendRouteDTO>(repository = frontendRouteRepository) {

    @Cacheable("frontend_routes_list")
    fun getAll(): List<FrontendRoute> {
        return frontendRouteRepository.findAllLeftJoin()
    }

    @Cacheable("frontend_route", key = "#id")
    override fun findById(id: UUID, includeInactive: Boolean): FrontendRoute {
        return super.findById(id, includeInactive)
    }

    @CacheEvict("frontend_routes_list", "frontend_route", allEntries = true)
    override fun update(dto: FrontendRouteDTO, id: UUID): FrontendRouteDTO {
        return super.update(dto, id)
    }

    @CacheEvict("frontend_routes_list", allEntries = true)
    override fun create(dto: FrontendRouteDTO): FrontendRouteDTO {
        return super.create(dto)
    }
    
    @CacheEvict("frontend_routes_list", "frontend_route", allEntries = true)
    override fun delete(id: UUID, hardDelete: Boolean): FrontendRouteDTO {
        return super.delete(id, hardDelete)
    }

    override fun newDTOToEntity(newDTO: FrontendRouteDTO): FrontendRoute {
        return FrontendRoute().apply {
            this.path = newDTO.path
            this.description = newDTO.description
            this.permissions = newDTO.permissions.map { it.toEntity() }.toMutableSet()
        }
    }

    override fun getSearchFields(): List<KProperty1<FrontendRoute, *>> {
        return listOf(
            FrontendRoute::path,
            FrontendRoute::description
        )
    }

    override fun updateEntityFromDTO(
        dto: FrontendRouteDTO,
        entity: FrontendRoute
    ) {
        entity.path = dto.path
        entity.description = dto.description
        entity.permissions = dto.permissions.map { it.toEntity() }.toMutableSet()
    }
}
