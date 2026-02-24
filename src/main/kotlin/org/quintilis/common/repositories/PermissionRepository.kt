package org.quintilis.common.repositories

import org.quintilis.common.entities.auth.Permission
import org.springframework.data.jpa.repository.JpaRepository

interface PermissionRepository : JpaRepository<Permission, Int>
