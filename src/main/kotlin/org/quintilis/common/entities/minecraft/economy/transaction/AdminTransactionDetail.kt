package org.quintilis.common.entities.minecraft.economy.transaction

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import org.quintilis.common.entities.minecraft.Player

@Entity
@Table(name = "admin_transaction_details", schema = "public")
open class AdminTransactionDetail {
    @Id
    @Column(name = "transaction_id", nullable = false)
    open var id: Int? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin_id", nullable = false)
    open var admin: Player? = null

}