package org.quintilis.common.entities.minecraft.economy.listing

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.quintilis.common.entities.minecraft.Player
import org.quintilis.common.entities.minecraft.economy.transaction.MarketTransactionDetail
import java.time.OffsetDateTime

@Entity
@Table(name = "listings", schema = "public")
open class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "seller_uuid", nullable = false)
    open var sellerUuid: Player? = null

    @NotNull
    @Column(name = "quantity", nullable = false)
    open var quantity: Int? = null

    @NotNull
    @Column(name = "asking_price_per_item", nullable = false)
    open var askingPricePerItem: Int? = null

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    open var createdAt: OffsetDateTime? = null

    @NotNull
    @ColumnDefault("(now() + '02:00:00'::interval)")
    @Column(name = "expires_at", nullable = false)
    open var expiresAt: OffsetDateTime? = null

    @NotNull
    @ColumnDefault("'ACTIVE'")
    @Column(name = "status", nullable = false, length = Integer.MAX_VALUE)
    open var status: String? = null

    @NotNull
    @Column(name = "item_data", nullable = false)
    open var itemData: ByteArray? = null

    @OneToMany(mappedBy = "listing")
    open var marketTransactionDetails: MutableSet<MarketTransactionDetail> =
        mutableSetOf()

}