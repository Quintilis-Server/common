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
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.Instant
import java.util.UUID
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.hibernate.annotations.ColumnDefault
import org.quintilis.common.dto.auth.UserDTO
import org.quintilis.common.dto.auth.UserSummaryDTO
import org.quintilis.common.entities.BaseEntity
import org.quintilis.common.entities.minecraft.Player

@Entity
@Table(name = "users", schema = "auth")
@NoArgsConstructor
@AllArgsConstructor
open class User : BaseEntity<UserDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    open var id: UUID? = null

    @NotNull
    @Column(name = "username", nullable = false, length = Integer.MAX_VALUE)
    open var username: String? = null

    @Column(name = "email", length = Integer.MAX_VALUE) open var email: String? = null

    @Size(max = 255) @Column(name = "password_hash") open var passwordHash: String? = null

    @Column(name = "microsoft_id", length = Integer.MAX_VALUE) open var microsoftId: String? = null

    @Column(name = "microsoft_uuid") open var microsoftUuid: UUID? = null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            schema = "auth",
            joinColumns = [JoinColumn(name = "user_id")],
            inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    open var roles: MutableSet<Role> = mutableSetOf()

    @Column(name = "avatar_path", length = Integer.MAX_VALUE) open var avatarPath: String? = null

    @NotNull
    @ColumnDefault("false")
    @Column(name = "is_verified", nullable = false)
    open var isVerified: Boolean = false

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    open var createdAt: Instant? = Instant.now()

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "mine_id") open var mine: Player? = null

    @Size(max = 255) @Column(name = "google_id") open var googleId: String? = null

    /** Returns all permission names from all roles assigned to this user. */
    fun getAllPermissions(): Set<String> {
        return roles.flatMap { it.permissions }.mapNotNull { it.name }.toSet()
    }

    fun getAllPermissionsEntity(): Set<Permission> {
        return roles.flatMap { it.permissions }.toSet()
    }

    /** Checks if the user has a specific permission. */
    fun hasPermission(permissionName: String): Boolean {
        return getAllPermissions().contains(permissionName)
    }

    fun hasPermission(permissions: Set<Permission>): Boolean {
        return getAllPermissions().containsAll(permissions.mapNotNull { it.name })
    }

    /** Returns the role with the highest priority. */
    fun getHighestRole(): Role? {
        return roles.maxByOrNull { it.priority ?: 0 }
    }

    override fun toDTO(): UserDTO {
        return UserDTO(
                id = this.id,
                username = this.username ?: "",
                email = this.email ?: "",
                roles = this.roles.map { it.toDTO() },
                avatarPath = this.avatarPath,
                isVerified = this.isVerified,
                createdAt = this.createdAt
        )
    }

    fun toSummaryDTO(): UserSummaryDTO {
        return UserSummaryDTO(
                id = this.id,
                username = this.username!!,
                roles = this.roles.mapNotNull { it.name },
                avatarPath = this.avatarPath,
                isVerified = this.isVerified
        )
    }
}
