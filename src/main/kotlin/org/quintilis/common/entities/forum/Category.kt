package org.quintilis.common.entities.forum

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "categories", schema = "forum")
open class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    open var id: UUID? = null

    @Size(max = 100)
    @NotNull
    @Column(name = "title", nullable = false, length = 100)
    open var title: String? = null

    @Size(max = 100)
    @NotNull
    @Column(name = "slug", nullable = false, length = 100)
    open var slug: String? = null

    @Column(name = "description", length = Integer.MAX_VALUE)
    open var description: String? = null

    @ColumnDefault("0")
    @Column(name = "display_order")
    open var displayOrder: Int? = null

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    open var createdAt: Instant? = null

}