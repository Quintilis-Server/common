package org.quintilis.common.controller

import jakarta.validation.Valid
import org.quintilis.common.dto.BaseDTO
import org.quintilis.common.entities.BaseEntity
import org.quintilis.common.exception.MethodNotAllowException
import org.quintilis.common.exception.UnauthorizedException
import org.quintilis.common.response.ApiResponse
import org.quintilis.common.service.BaseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID

abstract class BaseController<E, ID : Any, DTO, NewDTO : Any>(
    private val service: BaseService<E, ID, DTO, NewDTO>
) where E : BaseEntity<DTO>, DTO : BaseDTO<E>, DTO : Any {
    protected open val allowCreate: Boolean = true
    protected open val allowUpdate: Boolean = true
    protected open val allowDelete: Boolean = true

    protected fun getAuthenticatedUserId(): UUID {
        val authentication = SecurityContextHolder.getContext().authentication

        if (authentication == null || !authentication.isAuthenticated) {
            throw UnauthorizedException("Usuário não autenticado. Faça login para continuar.")
        }

        // 1. Como somos um Resource Server, o token chega como JwtAuthenticationToken.
        // Vamos extrair o 'user_id' que o seu CustomTokenCustomizer injetou!
        if (authentication is JwtAuthenticationToken) {
            val userIdClaim = authentication.token.getClaimAsString("user_id")
            if (userIdClaim != null) {
                return UUID.fromString(userIdClaim)
            }
        }

        // 2. Fallback: Se por algum motivo o claim não existir, tenta ler o 'name' direto
        return try {
            UUID.fromString(authentication.name)
        } catch (ex: IllegalArgumentException) {
            throw UnauthorizedException("Não foi possível identificar o usuário autenticado no token.")
        }
    }

    @GetMapping("/all")
    fun findAll(
        @RequestParam(required = false) search: String?,
        @PageableDefault(page = 0, size = 10) pageable: Pageable
    ): ApiResponse<Page<DTO>> {
        val pageResult = service.findAll(search, pageable)
        return ApiResponse.success(pageResult)
    }

    @GetMapping("/{id}")
    open fun findById(
        @PathVariable id: ID,
    ): ApiResponse<DTO> {
        return ApiResponse.success(service.findById(id).toDTO())
    }

    @GetMapping("/all/with-inactive")
    fun findAllWithInactive(
        @RequestParam(required = false) search: String?,
        @PageableDefault(page = 0, size = 10) pageable: Pageable
    ): ApiResponse<Page<DTO>> {
        val pageResult = service.findAll(search, pageable, includeInactive = true)
        return ApiResponse.success(pageResult)
    }

    @GetMapping("/{id}/with-inactive")
    fun findByIdWithInative(@PathVariable id: ID): ApiResponse<DTO> {
        return ApiResponse.success(service.findById(id, includeInactive = true).toDTO())
    }

    @PostMapping("/new")
    open fun create(@Valid @RequestBody dto: NewDTO): ApiResponse<DTO> {
        if (!allowCreate) throw MethodNotAllowException("Criação desabilitada para este recurso.")

        return ApiResponse.success(service.create(dto))
    }

    @PostMapping("/{id}/update")
    open fun update(
        @PathVariable id: ID,
        @RequestBody dto: DTO,
    ): ApiResponse<DTO> {
        if(!allowUpdate) throw MethodNotAllowException("Edição desabilitada para este recurso.")
        val dto = service.update(dto, id)
        return ApiResponse.success(dto)
    }

    @DeleteMapping("/{id}")
    open fun delete(
        @PathVariable id: ID,
    ): ApiResponse<DTO> {
        if(!allowDelete) throw MethodNotAllowException("Exclusão desabilitada para este recurso.")
        return ApiResponse.success( service.delete(id))
    }

    @DeleteMapping("/{id}/force")
    open fun deleteForce(
        @PathVariable id: ID,
    ): ApiResponse<DTO> {
        if(!allowDelete) throw MethodNotAllowException("Exclusão desabilitada para este recurso.")
        return ApiResponse.success( service.delete(id, hardDelete = true))
    }
}