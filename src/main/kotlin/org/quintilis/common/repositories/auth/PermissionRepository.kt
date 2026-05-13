package org.quintilis.common.repositories.auth

import org.quintilis.common.entities.auth.Permission
import org.quintilis.common.repositories.BaseRepository

interface PermissionRepository : BaseRepository<Permission, Int> {
    fun findByName(name: String): Permission?
    fun findAllByNameIn(names: MutableCollection<String>): List<Permission>
    fun findByIsActive(active: Boolean = true): List<Permission>
}
