package org.quintilis.common.repositories.auth

import org.quintilis.common.entities.auth.FrontendRoute
import org.quintilis.common.repositories.BaseRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface FrontendRouteRepository : BaseRepository<FrontendRoute, UUID> {
    
    @Query("SELECT r FROM FrontendRoute r LEFT JOIN FETCH r.permissions")
    fun findAllLeftJoin(): List<FrontendRoute>
}
