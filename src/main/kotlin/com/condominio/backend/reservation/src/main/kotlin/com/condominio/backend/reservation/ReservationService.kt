package com.condominio.backend.reservation

import org.springframework.stereotype.Service

@Service
class ReservationService(
    private val repository: ReservationRepository
) {

    fun create(
        reservation: Reservation
    ): Reservation {

        if (
            repository.existsBySpaceNameAndReservationDate(
                reservation.spaceName,
                reservation.reservationDate!!
            )
        ) {
            throw RuntimeException(
                "Horário já reservado"
            )
        }

        reservation.status =
            ReservationStatus.PENDING

        return repository.save(reservation)
    }

    fun approve(
        id: Long
    ): Reservation {

        val reservation =
            repository.findById(id)
                .orElseThrow {
                    RuntimeException(
                        "Reserva não encontrada"
                    )
                }

        reservation.status =
            ReservationStatus.APPROVED

        return repository.save(reservation)
    }

    fun reject(
        id: Long
    ): Reservation {

        val reservation =
            repository.findById(id)
                .orElseThrow {
                    RuntimeException(
                        "Reserva não encontrada"
                    )
                }

        reservation.status =
            ReservationStatus.REJECTED

        return repository.save(reservation)
    }

    fun findAll(): List<Reservation> {
        return repository.findAll()
    }
}