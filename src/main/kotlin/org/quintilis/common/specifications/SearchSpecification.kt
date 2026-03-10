package org.quintilis.common.specifications

import org.springframework.data.jpa.domain.Specification

object SearchSpecification {

    fun <T : Any> searchInFields(searchTerm: String?, fields: List<String>): Specification<T>? {
        if(searchTerm.isNullOrBlank() || fields.isEmpty()) {
            return null
        }

        return Specification<T> { root, _, criteriaBuilder ->
            val pattern = "%${searchTerm.lowercase()}%"

            val predicates = fields.map { field ->
                // Equivalente SQL: LOWER(campo) LIKE '%abacate%'
                criteriaBuilder.like(criteriaBuilder.lower(root.get(field)), pattern)
            }
            criteriaBuilder.or(*predicates.toTypedArray())
        }
    }
}