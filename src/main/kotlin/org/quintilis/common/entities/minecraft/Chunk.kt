package org.quintilis.common.entities.minecraft

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import org.quintilis.common.entities.minecraft.factions.clan.ClanChunk
import java.util.UUID

@Entity
@Table(name = "chunk", schema = "public")
open class Chunk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @NotNull
    @Column(name = "world_uuid", nullable = false)
    open var worldUuid: UUID? = null

    @NotNull
    @Column(name = "chunk_x", nullable = false)
    open var chunkX: Int? = null

    @NotNull
    @Column(name = "chunk_z", nullable = false)
    open var chunkZ: Int? = null

    @OneToOne(mappedBy = "chunk")
    open var clanChunk: ClanChunk? = null

}