package org.quintilis.common.entities.minecraft.factions.clan

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.quintilis.common.entities.minecraft.Player
import java.time.OffsetDateTime

@Entity
@Table(name = "clans", schema = "public")
open class Clan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @Size(max = 30)
    @NotNull
    @Column(name = "name", nullable = false, length = 30)
    open var name: String? = null

    @Size(max = 5)
    @Column(name = "tag", length = 5)
    open var tag: String? = null

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "leader_uuid", nullable = false)
    open var leaderUuid: Player? = null

    @NotNull
    @ColumnDefault("true")
    @Column(name = "active", nullable = false)
    open var active: Boolean? = null

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    open var createdAt: OffsetDateTime? = null

    @NotNull
    @ColumnDefault("0")
    @Column(name = "points", nullable = false)
    open var points: Int? = null

    @Column(name = "deleted_at")
    open var deletedAt: OffsetDateTime? = null

}