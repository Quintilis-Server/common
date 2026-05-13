package org.quintilis.common.dto

import java.io.Serializable
import java.time.Instant

abstract class BaseDTO<E, ID> : Serializable {
    open var id: ID? = null
    open var createdAt: Instant = Instant.now()
    abstract fun toEntity(): E
}
