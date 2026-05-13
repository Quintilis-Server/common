package org.quintilis.common.repositories.auth

import org.quintilis.common.entities.auth.Role
import org.quintilis.common.repositories.BaseRepository

interface RoleRepository : BaseRepository<Role, Int> {
    fun findByName(name: String): Role?
}
