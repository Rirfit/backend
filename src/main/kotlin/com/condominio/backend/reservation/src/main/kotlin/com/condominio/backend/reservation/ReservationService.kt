package com.condominio.backend.reservation

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReservationService(
private val repository: ReservationRepository
) {


// RN01 - Reserva deve iniciar como PENDING
// RN02 - Não deve permitir conflito de reserva
// RN03 - Reserva não pode possuir data passada
// RN04 - Reserva deve possuir nome do espaço
fun create(
    reservation: Reservation
): Reservation {

    if (
        reservation.spaceName.isBlank()
    ) {
        throw RuntimeException(
            "Nome do espaço obrigatório"
        )
    }

    if (
        reservation.reservationDate == null
    ) {
        throw RuntimeException(
            "Data da reserva obrigatória"
        )
    }

    if (
        reservation.reservationDate!!.isBefore(
            LocalDateTime.now()
        )
    ) {
        throw RuntimeException(
            "Reserva não pode ser em data passada"
        )
    }

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

// RN05 - Reserva aprovada deve mudar status
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

// RN06 - Reserva rejeitada deve mudar status
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
