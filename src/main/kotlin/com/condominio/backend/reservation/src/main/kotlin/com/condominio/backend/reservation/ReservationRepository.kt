package com.condominio.backend.reservation

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface ReservationRepository :
    JpaRepository<Reservation, Long> {

    fun existsBySpaceNameAndReservationDate(
        spaceName: String,
        reservationDate: LocalDateTime
    ): Boolean
}