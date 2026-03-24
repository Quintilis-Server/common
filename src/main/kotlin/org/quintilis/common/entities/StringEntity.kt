package org.quintilis.common.entities

import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.util.UUID

@MappedSuperclass
abstract class StringEntity<D> : BaseEntity<D>() {
    @Id
    @Column(name = "id", nullable = false, updatable = false, length = 100)
    open var id: String = ""
}