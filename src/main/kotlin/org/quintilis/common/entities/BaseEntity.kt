package org.quintilis.common.entities

import java.io.Serializable

interface BaseEntity<D> : Serializable {
    fun toDTO(): D
}
