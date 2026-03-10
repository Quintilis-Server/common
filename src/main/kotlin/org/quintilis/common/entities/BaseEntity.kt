package org.quintilis.common.entities

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import java.io.Serializable
import java.time.Instant

@MappedSuperclass
abstract class BaseEntity< D> : Serializable {
    @Column(name = "is_active", nullable = false, updatable = false)
    var isActive: Boolean = true

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: Instant? = Instant.now()
    abstract fun toDTO(): D
}
