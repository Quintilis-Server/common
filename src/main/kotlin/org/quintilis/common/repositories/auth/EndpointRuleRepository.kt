package org.quintilis.common.repositories.auth

import org.quintilis.common.entities.auth.EndpointRule
import org.quintilis.common.repositories.BaseRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface EndpointRuleRepository: BaseRepository<EndpointRule, UUID> {
    @Query("""
        SELECT DISTINCT e FROM EndpointRule e
        LEFT JOIN FETCH e.permissions
    """)
    fun findAllLeftJoin(): List<EndpointRule>

    // No seu EndpointRuleRepository.kt, adicione esta linha:
    fun existsByHttpMethodAndUrlPattern(httpMethod: String, urlPattern: String): Boolean

    fun findByHttpMethodAndUrlPatternAndServiceName(httpMethod: String, urlPattern: String, serviceName: String): EndpointRule?

    fun findByServiceName(serviceName: String): List<EndpointRule>
}