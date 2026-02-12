package org.quintilis.common.entities.auth

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "login_logs", schema = "auth")
open class LoginLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    open var id: UUID? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    open var user: User? = null

    @Size(max = 45)
    @Column(name = "ip_address", length = 45)
    open var ipAddress: String? = null

    @Size(max = 20)
    @NotNull
    @Column(name = "login_method", nullable = false, length = 20)
    open var loginMethod: String? = null

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "\"timestamp\"", nullable = false)
    open var timestamp: Instant? = null

}