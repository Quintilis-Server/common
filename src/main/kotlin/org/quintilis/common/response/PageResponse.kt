package org.quintilis.common.response

import java.io.Serializable

data class PageResponse<T>(
    val totalPages: Int,
    val currentPage: Int,
    val items: List<T>
): Serializable
