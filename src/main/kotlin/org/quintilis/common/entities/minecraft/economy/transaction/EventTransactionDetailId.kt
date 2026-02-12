package org.quintilis.common.entities.minecraft.economy.transaction

import jakarta.persistence.Embeddable
import org.hibernate.Hibernate
import java.io.Serializable
import java.util.Objects

@Embeddable
open class EventTransactionDetailId : Serializable {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

        other as EventTransactionDetailId

        return
    }

    override fun hashCode(): Int = Objects.hash()

    companion object {
        private const val serialVersionUID = 0L
    }
}