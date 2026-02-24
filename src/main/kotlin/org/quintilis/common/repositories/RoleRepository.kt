
package org.quintilis.common.repositories

import org.quintilis.common.entities.auth.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Int> {
    fun findByName(name: String): Role?
}
