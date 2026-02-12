package org.quintilis.common.entities.minecraft.log

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import org.quintilis.common.entities.minecraft.Player
import org.quintilis.common.entities.minecraft.factions.clan.Clan
import java.time.OffsetDateTime
import java.util.UUID

@Entity
@Table(name = "chat_logs", schema = "public")
open class ChatLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    open var player: Player? = null

    @NotNull
    @Column(name = "content", nullable = false, length = Integer.MAX_VALUE)
    open var content: String? = null

    @Size(max = 32)
    @NotNull
    @Column(name = "channel", nullable = false, length = 32)
    open var channel: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id")
    open var recipient: Player? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clan_id")
    open var clan: Clan? = null

    @Column(name = "location_x")
    open var locationX: Int? = null

    @Column(name = "location_z")
    open var locationZ: Int? = null

    @Column(name = "world_uuid")
    open var worldUuid: UUID? = null

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    open var createdAt: OffsetDateTime? = null

}