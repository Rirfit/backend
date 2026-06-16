package com.condominio.backend.user.dto

import com.condominio.backend.apartment.Apartment
import com.condominio.backend.user.Role

data class UserResponse(
    val id: Long?,
    val name: String,
    val email: String,
    val role: Role,
    val active: Boolean,

    val telefone: String,
    val block: String,
    val number: String
)