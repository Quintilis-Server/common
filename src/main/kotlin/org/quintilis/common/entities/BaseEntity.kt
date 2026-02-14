package org.quintilis.common.entities

interface BaseEntity<D> {
    fun toDTO(): D
}
