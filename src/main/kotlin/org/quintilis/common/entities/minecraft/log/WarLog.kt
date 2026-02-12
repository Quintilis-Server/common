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
import org.hibernate.annotations.ColumnDefault
import org.quintilis.common.entities.minecraft.Player
import org.quintilis.common.entities.minecraft.factions.clan.Clan
import org.quintilis.common.entities.minecraft.factions.clan.ClanCore
import java.time.OffsetDateTime

@Entity
@Table(name = "war_logs", schema = "public")
open class WarLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attacker_id", nullable = false)
    open var attacker: Player? = null

    @NotNull
    @Column(name = "attacker_name", nullable = false, length = Integer.MAX_VALUE)
    open var attackerName: String? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attacker_clan_id", nullable = false)
    open var attackerClan: Clan? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "defender_clan_id", nullable = false)
    open var defenderClan: Clan? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "target_core", nullable = false)
    open var targetCore: ClanCore? = null

    @NotNull
    @Column(name = "damage", nullable = false)
    open var damage: Int? = null

    @NotNull
    @Column(name = "remaining_health", nullable = false)
    open var remainingHealth: Int? = null

    @NotNull
    @Column(name = "x", nullable = false)
    open var x: Int? = null

    @NotNull
    @Column(name = "y", nullable = false)
    open var y: Int? = null

    @NotNull
    @Column(name = "z", nullable = false)
    open var z: Int? = null

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    open var createdAt: OffsetDateTime? = null

}