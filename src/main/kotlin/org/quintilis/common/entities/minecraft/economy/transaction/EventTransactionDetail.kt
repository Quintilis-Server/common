package org.quintilis.common.entities.minecraft.economy.transaction

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table
import org.quintilis.common.entities.minecraft.economy.event.Event

@Entity
@Table(name = "event_transaction_details", schema = "public")
open class EventTransactionDetail {
    @EmbeddedId
    open var id: EventTransactionDetailId? = null

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    open var event: Event? = null

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transaction_id", nullable = false)
    open var transaction: Transaction? = null

    @Column(name = "prize")
    open var prize: Int? = null

    @Column(name = "multiplier")
    open var multiplier: Double? = null

}