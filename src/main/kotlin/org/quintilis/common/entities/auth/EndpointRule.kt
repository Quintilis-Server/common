package org.quintilis.common.entities.auth

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.Size
import lombok.NoArgsConstructor
import org.hibernate.annotations.BatchSize
import org.hibernate.annotations.SQLRestriction
import org.quintilis.common.dto.auth.EndpointRuleDTO
import org.quintilis.common.entities.BaseEntity
import java.util.UUID

@Entity
@Table(name = "endpoint_rules", schema = "auth")
class EndpointRule: BaseEntity<EndpointRuleDTO>() {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    open var id: UUID? = null

    @Column(name = "service_name")
    var serviceName: String? = ""

    @Column(name = "http_method")
    @Size(max = 6)
    var httpMethod: String? = ""

    @Column(name = "url_pattern")
    var urlPattern: String? = ""

    @BatchSize(size = 50)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "endpoint_rule_permissions",
        schema = "auth",
        joinColumns = [JoinColumn(name = "endpoint_id")],
        inverseJoinColumns = [JoinColumn(name = "permission_id")]
    )
    open var permissions: MutableSet<Permission> = mutableSetOf()

    @Column
    var description: String? = null

    override fun toDTO(): EndpointRuleDTO {
        return EndpointRuleDTO(id!!, httpMethod!!, urlPattern!!, serviceName!!, permissions.map { it.toDTO() }.toSet(), description)
    }
}