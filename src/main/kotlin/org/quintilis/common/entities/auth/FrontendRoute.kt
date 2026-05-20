package org.quintilis.common.entities.auth

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.hibernate.annotations.BatchSize
import org.quintilis.common.dto.auth.FrontendRouteDTO
import org.quintilis.common.entities.UuidEntity

@Entity
@Table(name = "frontend_routes", schema = "auth")
class FrontendRoute : UuidEntity<FrontendRouteDTO>() {

    @Column(name = "path", nullable = false, unique = true)
    var path: String? = null

    @Column
    var description: String? = null

    @BatchSize(size = 50)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "frontend_route_permissions",
        schema = "auth",
        joinColumns = [JoinColumn(name = "route_id")],
        inverseJoinColumns = [JoinColumn(name = "permission_id")]
    )
    var permissions: MutableSet<Permission> = mutableSetOf()

    override fun toDTO(): FrontendRouteDTO {
        return FrontendRouteDTO(
            path = this.path!!,
            description = this.description,
            permissions = this.permissions.map { it.toDTO() }.toSet()
        ).apply {
            this.id = this@FrontendRoute.id
            this.createdAt = this@FrontendRoute.createdAt!!
        }
    }
}
