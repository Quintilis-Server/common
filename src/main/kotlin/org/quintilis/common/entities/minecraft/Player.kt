package org.quintilis.common.entities.minecraft

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.quintilis.common.entities.auth.User
import java.util.UUID

@Entity
@Table(name = "players", schema = "public")
open class Player {
    @Id
    @Column(name = "id", nullable = false)
    open var id: UUID? = null

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    open var name: String? = null

    @NotNull
    @Column(name = "points", nullable = false)
    open var points: Int? = null
}