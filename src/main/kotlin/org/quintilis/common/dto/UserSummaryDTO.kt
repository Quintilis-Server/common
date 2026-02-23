package org.quintilis.common.dto

import java.util.UUID
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@NoArgsConstructor
@AllArgsConstructor
data class UserSummaryDTO(
        var id: UUID?,
        var username: String,
        var role: String,
        var avatarPath: String? = null,
        var isVerified: Boolean = false,
)
