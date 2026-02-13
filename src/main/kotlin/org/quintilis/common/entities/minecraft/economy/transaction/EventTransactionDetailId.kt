package org.quintilis.common.entities.minecraft.economy.transaction

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.Hibernate
import java.io.Serializable
import java.util.Objects

@Embeddable
open class EventTransactionDetailId(

    // O tipo aqui DEVE ser o mesmo tipo da chave primária das tabelas originais (Integer)
    @Column(name = "event_id")
    open var eventId: Int? = null,

    @Column(name = "transaction_id")
    open var transactionId: Int? = null

) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

        other as EventTransactionDetailId

        return eventId == other.eventId && transactionId == other.transactionId
    }

    override fun hashCode(): Int = Objects.hash(eventId, transactionId)

    companion object {
        private const val serialVersionUID = 1L
    }
}