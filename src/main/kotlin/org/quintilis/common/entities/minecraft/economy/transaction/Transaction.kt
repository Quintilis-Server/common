package org.quintilis.common.entities.minecraft.economy.transaction

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import org.quintilis.common.entities.minecraft.Player
import org.quintilis.common.entities.minecraft.factions.clan.ClanChunk
import java.time.OffsetDateTime

@Entity
@Table(name = "transactions", schema = "public")
open class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    open var player: Player? = null

    @Size(max = 255)
    @NotNull
    @Column(name = "transaction_type", nullable = false)
    open var transactionType: String? = null

    @NotNull
    @Column(name = "change", nullable = false)
    open var change: Int? = null

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "\"time\"", nullable = false)
    open var time: OffsetDateTime? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    open var parent: Transaction? = null

    @OneToMany(mappedBy = "transaction")
    open var clanChunks: MutableSet<ClanChunk> = mutableSetOf()

    @OneToMany(mappedBy = "transaction")
    open var eventTransactionDetails: MutableSet<EventTransactionDetail> = mutableSetOf()

    @OneToOne
    open var marketTransactionDetail: MarketTransactionDetail? = null

    @OneToOne(mappedBy = "parent")
    open var transaction: Transaction? = null

}