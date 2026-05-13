package org.quintilis.common.entities.minecraft

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.quintilis.common.dto.minecraft.MineEventDTO
import org.quintilis.common.entities.IntEntity
import java.time.Instant

@Entity
@Table(name = "events", schema = "public")
class MineEvent: IntEntity<MineEventDTO>() {
    @Column
    @NotNull
    var name: String? = null

    @Column
    @NotNull
    var description: String? = null

    @Column(name = "starts_at")
    @NotNull
    var startsAt: Instant? = null

    @Column(name = "ends_at")
    @NotNull
    var endsAt: Instant? = null

    @Column()
    var visible: Boolean? = false


    override fun toDTO(): MineEventDTO {
        println(this.id)
        return MineEventDTO(
            name = this.name ?: "",
            description = this.description ?: "",
            startsAt = this.startsAt ?: Instant.now(),
            endsAt = this.endsAt ?: Instant.now(),
            visible = this.visible ?: false,
        ).apply {
            // Preenchemos os campos herdados da BaseDTO
            this.id = this@MineEvent.id
            this.createdAt = this@MineEvent.createdAt!!
        }
    }
}