package org.quintilis.common.entities.auth

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.hibernate.annotations.ColumnDefault
import org.quintilis.common.entities.minecraft.Player
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "users", schema = "auth")
@NoArgsConstructor
@AllArgsConstructor
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    open var id: UUID? = null

    @NotNull
    @Column(name = "username", nullable = false, length = Integer.MAX_VALUE)
    open var username: String? = null

    @Column(name = "email", length = Integer.MAX_VALUE)
    open var email: String? = null

    @Size(max = 255)
    @Column(name = "password_hash")
    open var passwordHash: String? = null

    @Column(name = "microsoft_id", length = Integer.MAX_VALUE)
    open var microsoftId: String? = null

    @Column(name = "microsoft_uuid")
    open var microsoftUuid: UUID? = null

    @Size(max = 20)
    @NotNull
    @ColumnDefault("'USER'")
    @Column(name = "role", nullable = false, length = 20)
    open var role: String? = null

    @Column(name = "avatar_path", length = Integer.MAX_VALUE)
    open var avatarPath: String? = null

    @NotNull
    @ColumnDefault("false")
    @Column(name = "is_verified", nullable = false)
    open var isVerified: Boolean = false

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    open var createdAt: Instant? = Instant.now()

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mine_id")
    open var mine: Player? = null

    @Size(max = 255)
    @Column(name = "google_id")
    open var googleId: String? = null
}