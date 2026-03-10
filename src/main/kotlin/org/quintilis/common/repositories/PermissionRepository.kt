package org.quintilis.common.repositories

import org.quintilis.common.dto.auth.PermissionDTO
import org.quintilis.common.entities.auth.Permission
import org.springframework.data.jpa.repository.JpaRepository

interface PermissionRepository : BaseRepository<Permission, Int> {
    fun findByName(name: String): Permission?
    fun findAllByNameIn(names: MutableCollection<String>): List<Permission>
    fun findByIsActive(active: Boolean = true): List<Permission>
}
