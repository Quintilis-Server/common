package org.quintilis.common.repositories

import org.quintilis.common.dto.BaseDTO
import org.quintilis.common.entities.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
// 🔥 2. Adicionado o ': Any' no ID
interface BaseRepository<T: BaseEntity<*>, ID : Any> : JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}