package org.quintilis.common.entities.auth

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.quintilis.common.dto.auth.PermissionDTO
import org.quintilis.common.entities.BaseEntity
import org.quintilis.common.entities.IntEntity

@Entity
@Table(name = "permissions", schema = "auth")
open class Permission : IntEntity<PermissionDTO>() {
    @Size(max = 80)
    @NotNull
    @Column(name = "name", nullable = false, length = 80)
    open var name: String? = null

    @Column(name = "description", length = Integer.MAX_VALUE) open var description: String? = null

    override fun toDTO(): PermissionDTO {
        return PermissionDTO(this.id!!, this.name!!, this.description ?: "")
    }
}
