package com.condominio.backend.user.dto

import com.condominio.backend.user.Role
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class CreateUserRequest(

    @field:NotBlank
    val name: String,

    @field:Email
    val email: String,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    val telefone: String,

    val apartmentId: Long,

    val role: Role = Role.RESIDENT
)
