package org.quintilis.common.dto.minecraft

import org.quintilis.common.dto.BaseDTO
import org.quintilis.common.entities.minecraft.MineEvent
import java.time.Instant

data class MineEventDTO(
    val name: String,
    val description: String,
    val startsAt: Instant,
    val endsAt: Instant,
    val visible: Boolean,
): BaseDTO<MineEvent, Int>() {
    override fun toEntity(): MineEvent {
        return MineEvent().apply {
            this.id = id // id herdado da BaseDTO
            this.name = name
            this.description = description
            this.startsAt = startsAt
            this.endsAt = endsAt
            this.visible = visible
            this.createdAt = createdAt // createdAt herdado da BaseDTO
        }
    }
}