package org.quintilis.common.dto

import java.util.UUID

data class UserSummaryDTO(
    val id: UUID?,
    val username: String,
    val role: String,
    val avatarPath: String? = null,
    val isVerified: Boolean = false,
)
