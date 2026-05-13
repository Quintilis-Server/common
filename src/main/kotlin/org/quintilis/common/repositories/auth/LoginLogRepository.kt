package org.quintilis.common.repositories.auth

import org.quintilis.common.entities.auth.LoginLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LoginLogRepository: JpaRepository<LoginLog, Int>
