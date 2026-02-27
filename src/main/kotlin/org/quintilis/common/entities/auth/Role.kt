package org.quintilis.common.entities.auth

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.Instant
import org.hibernate.annotations.ColumnDefault
import org.quintilis.common.dto.auth.RoleDTO
import org.quintilis.common.entities.BaseEntity

@Entity
@Table(name = "roles", schema = "auth")
open class Role : BaseEntity<RoleDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @Size(max = 30)
    @NotNull
    @Column(name = "name", nullable = false, length = 30)
    open var name: String? = null

    @NotNull
    @Column(name = "display_name", nullable = false, length = Integer.MAX_VALUE)
    open var displayName: String? = null

    @Size(max = 7)
    @NotNull
    @Column(name = "color", nullable = false, length = 7)
    open var color: String? = null

    @Size(max = 50) @Column(name = "icon", length = 50) open var icon: String? = null

    @NotNull
    @ColumnDefault("0")
    @Column(name = "priority", nullable = false)
    open var priority: Int? = null

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    open var createdAt: Instant = Instant.now()

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permissions",
            schema = "auth",
            joinColumns = [JoinColumn(name = "role_id")],
            inverseJoinColumns = [JoinColumn(name = "permission_id")]
    )
    open var permissions: MutableSet<Permission> = mutableSetOf()

    override fun toDTO(): RoleDTO {
        return RoleDTO(
                this.id!!,
                this.name!!,
                this.displayName!!,
                this.color!!,
                this.icon ?: "",
                this.priority!!,
                this.createdAt!!,
                this.permissions.mapNotNull { it.name }
        )
    }
}
