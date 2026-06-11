package com.condominio.backend.reservation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.time.LocalDateTime

class ReservationServiceTest {

    /*
 * MOCK:
 * Simula o comportamento do repositório
 * sem acessar o banco real.
 */


private val repository:
    ReservationRepository = mock()

private val service =
    ReservationService(repository)

// RN13 - Reserva deve iniciar com status PENDING
@Test
fun `deve criar reserva com sucesso`() {

    val reservation =
        Reservation(
            spaceName = "Churrasqueira",
            reservationDate =
                LocalDateTime.now()
                    .plusDays(1)
        )

/*
 * STUB:
 * Simula retorno esperado para o teste.
 */

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

// RN14 - Não deve permitir conflito de reserva
@Test
fun `deve impedir conflito de reserva`() {

    val reservation =
        Reservation(
            spaceName = "Churrasqueira",
            reservationDate =
                LocalDateTime.now()
                    .plusDays(1)
        )

    whenever(
        repository
            .existsBySpaceNameAndReservationDate(
                any(),
                any()
            )
    ).thenReturn(true)

    assertThrows<RuntimeException> {

        service.create(reservation)
    }

/*
 * VERIFY:
 * Verifica se o método esperado foi executado.
 */

    verify(repository, never())
        .save(any())
}

// RN15 - Reserva não pode possuir data passada
@Test
fun `deve impedir reserva em data passada`() {

    val reservation =
        Reservation(
            spaceName = "Salão",
            reservationDate =
                LocalDateTime.now()
                    .minusDays(1)
        )

    assertThrows<RuntimeException> {

        service.create(reservation)
    }

    verify(repository, never())
        .save(any())
}

// RN16 - Reserva deve possuir nome do espaço
@Test
fun `deve impedir reserva sem nome do espaco`() {

    val reservation =
        Reservation(
            spaceName = "",
            reservationDate =
                LocalDateTime.now()
                    .plusDays(1)
        )

    assertThrows<RuntimeException> {

        service.create(reservation)
    }

    verify(repository, never())
        .save(any())
}


}
