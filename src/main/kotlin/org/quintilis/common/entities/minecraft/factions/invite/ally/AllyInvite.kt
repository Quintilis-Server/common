package org.quintilis.common.entities.minecraft.factions.invite.ally

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
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.quintilis.common.entities.minecraft.factions.clan.Clan
import java.time.OffsetDateTime

@Entity
@Table(name = "ally_invite", schema = "public")
open class AllyInvite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "sender_clan_id", nullable = false)
    open var senderClan: Clan? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "target_clan_id", nullable = false)
    open var targetClan: Clan? = null

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    open var createdAt: OffsetDateTime? = null

    @NotNull
    @ColumnDefault("true")
    @Column(name = "active", nullable = false)
    open var active: Boolean? = null

    @Size(max = 20)
    @NotNull
    @ColumnDefault("'PENDING'")
    @Column(name = "status", nullable = false, length = 20)
    open var status: String? = null

    @NotNull
    @Column(name = "expires_at", nullable = false)
    open var expiresAt: OffsetDateTime? = null

    @Column(name = "updated_at")
    open var updatedAt: OffsetDateTime? = null

}