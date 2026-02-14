package org.quintilis.common.dto

import java.io.Serializable

interface BaseDTO<E> : Serializable {
    fun toEntity(): E
}
