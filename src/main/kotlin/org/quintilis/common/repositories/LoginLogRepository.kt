package org.quintilis.common.repositories

import org.quintilis.common.entities.auth.LoginLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface LoginLogRepository : JpaRepository<LoginLog, UUID>
