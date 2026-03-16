package org.quintilis.common.entities

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.ColumnDefault
import java.io.Serializable
import java.time.Instant

@MappedSuperclass
abstract class BaseEntity< D> : Serializable {
    @ColumnDefault("TRUE")
    @Column(name = "is_active", nullable = false, updatable = false)
    var isActive: Boolean = true

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: Instant? = Instant.now()
    abstract fun toDTO(): D
}
