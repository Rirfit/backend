package com.condominio.backend.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun existsByEmail(email: String): Boolean

    fun countByApartmentId(
        apartmentId: Long
    ): Long
}
