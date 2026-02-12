package org.quintilis.common.entities.minecraft.factions.clan

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
import java.time.OffsetDateTime

@Entity
@Table(name = "clan_relation", schema = "public")
open class ClanRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clan1_id", nullable = false)
    open var clan1: Clan? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clan2_id", nullable = false)
    open var clan2: Clan? = null

    @Size(max = 32)
    @NotNull
    @Column(name = "relation", nullable = false, length = 32)
    open var relation: String? = null

    @NotNull
    @ColumnDefault("true")
    @Column(name = "active", nullable = false)
    open var active: Boolean? = null

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    open var createdAt: OffsetDateTime? = null

}