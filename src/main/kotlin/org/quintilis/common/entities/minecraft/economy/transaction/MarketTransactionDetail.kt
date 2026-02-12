package org.quintilis.common.entities.minecraft.economy.transaction

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import org.quintilis.common.entities.minecraft.economy.listing.Listing

@Entity
@Table(name = "market_transaction_details", schema = "public")
open class MarketTransactionDetail {
    @Id
    @Column(name = "transaction_id", nullable = false)
    open var id: Int? = null

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transaction_id", nullable = false)
    open var transactions: Transaction? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "listing_id", nullable = false)
    open var listing: Listing? = null

    @NotNull
    @Column(name = "quantity", nullable = false)
    open var quantity: Int? = null

}