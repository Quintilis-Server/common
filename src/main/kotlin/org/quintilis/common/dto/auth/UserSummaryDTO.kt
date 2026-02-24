package org.quintilis.common.dto.auth

import java.util.UUID
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@NoArgsConstructor
@AllArgsConstructor
data class UserSummaryDTO(
        var id: UUID?,
        var username: String,
        var roles: List<String> = emptyList(),
        var avatarPath: String? = null,
        var isVerified: Boolean = false,
)
