package org.quintilis.common.entities.minecraft.economy.event

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import org.quintilis.common.entities.minecraft.economy.transaction.EventTransactionDetail
import java.time.OffsetDateTime

@Entity
@Table(name = "events", schema = "public")
open class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    open var name: String? = null

    @NotNull
    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    open var description: String? = null

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    open var createdAt: OffsetDateTime? = null

    @NotNull
    @Column(name = "starts_at", nullable = false)
    open var startsAt: OffsetDateTime? = null

    @NotNull
    @Column(name = "ends_at", nullable = false)
    open var endsAt: OffsetDateTime? = null

    @NotNull
    @ColumnDefault("true")
    @Column(name = "active", nullable = false)
    open var active: Boolean? = null

    @NotNull
    @ColumnDefault("false")
    @Column(name = "visible", nullable = false)
    open var visible: Boolean? = null

    @OneToMany(mappedBy = "event")
    open var eventTransactionDetails: MutableSet<EventTransactionDetail> = mutableSetOf()

}