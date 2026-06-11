package com.condominio.backend.apartment

import org.springframework.data.jpa.repository.JpaRepository

interface ApartmentRepository : JpaRepository<Apartment, Long> {

    fun existsByBlockAndNumber(
        block: String,
        number: String
    ): Boolean
}
