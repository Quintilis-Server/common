package org.quintilis.common.entities.minecraft.factions.clan

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.time.OffsetDateTime

@Entity
@Table(name = "clan_cores", schema = "public")
open class ClanCore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @Column(name = "x")
    open var x: Int? = null

    @Column(name = "y")
    open var y: Int? = null

    @Column(name = "z")
    open var z: Int? = null

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clan_id", nullable = false)
    open var clan: Clan? = null

    @NotNull
    @ColumnDefault("1000")
    @Column(name = "health", nullable = false)
    open var health: Int? = null

    @NotNull
    @ColumnDefault("true")
    @Column(name = "active", nullable = false)
    open var active: Boolean? = null

    @Column(name = "deleted_at")
    open var deletedAt: OffsetDateTime? = null

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    open var createdAt: OffsetDateTime? = null

    @Size(max = 16)
    @NotNull
    @ColumnDefault("'SUB_CORE'")
    @Column(name = "type", nullable = false, length = 16)
    open var type: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_core")
    open var parentCore: ClanCore? = null

    @NotNull
    @ColumnDefault("false")
    @Column(name = "placed", nullable = false)
    open var placed: Boolean? = null

    @Column(name = "placed_at")
    open var placedAt: OffsetDateTime? = null

}