package org.quintilis.common.entities.minecraft.factions.invite.player

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

@Entity
@Table(name = "member_invite", schema = "public")
open class MemberInvite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clan_id", nullable = false)
    open var clan: Clan? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    open var player: Player? = null

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    open var createdAt: OffsetDateTime? = null

    @NotNull
    @ColumnDefault("(now() + '00:05:00'::interval)")
    @Column(name = "expires_at", nullable = false)
    open var expiresAt: OffsetDateTime? = null

    @NotNull
    @Column(name = "active", nullable = false)
    open var active: Boolean? = null

    @Size(max = 20)
    @NotNull
    @ColumnDefault("'PENDING'")
    @Column(name = "status", nullable = false, length = 20)
    open var status: String? = null

}