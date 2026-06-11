package com.condominio.backend.reservation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.time.LocalDateTime

class ReservationServiceTest {

    private val repository:
        ReservationRepository = mock()

    private val service =
        ReservationService(repository)

    @Test
    fun `deve criar reserva com sucesso`() {

        val reservation =
            Reservation(
                spaceName = "Churrasqueira",
                reservationDate =
                    LocalDateTime.now()
            )

        whenever(
            repository
                .existsBySpaceNameAndReservationDate(
                    any(),
                    any()
                )
        ).thenReturn(false)

        doAnswer {
            it.arguments[0]
        }.whenever(repository)
            .save(any())

        val result =
            service.create(reservation)

        assertEquals(
            ReservationStatus.PENDING,
            result.status
        )
    }
}