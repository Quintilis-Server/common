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
import org.quintilis.common.entities.minecraft.factions.clan.Clan
import java.time.OffsetDateTime
import java.util.UUID

@Entity
@Table(name = "action_log", schema = "public")
open class ActionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @Size(max = 50)
    @NotNull
    @Column(name = "action_type", nullable = false, length = 50)
    open var actionType: String? = null

    @NotNull
    @Column(name = "actor_id", nullable = false)
    open var actorId: UUID? = null

    @Column(name = "target_id")
    open var targetId: UUID? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clan_id")
    open var clan: Clan? = null

    @Column(name = "details", length = Integer.MAX_VALUE)
    open var details: String? = null

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    open var createdAt: OffsetDateTime? = null

}