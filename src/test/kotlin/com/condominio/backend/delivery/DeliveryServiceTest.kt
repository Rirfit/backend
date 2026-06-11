package com.condominio.backend.delivery

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.*

class DeliveryServiceTest {


/*
 * MOCK:
 * Simula o comportamento do repositório
 * sem acessar o banco real.
 */

private val repository:
    DeliveryRepository = mock()

private val service =
    DeliveryService(repository)

// RN20 - Encomenda deve iniciar com status PENDING
@Test
fun `deve criar encomenda com sucesso`() {

    val delivery =
        Delivery(
            description = "Amazon"
        )

/*
 * STUB:
 * Simula retorno esperado para o teste.
 */

    doAnswer {
        it.arguments[0]
    }.whenever(repository)
        .save(any())

    val result =
        service.create(delivery)

    assertEquals(
        DeliveryStatus.PENDING,
        result.status
    )
}

// RN21 - Encomenda retirada deve alterar status para PICKED_UP
@Test
fun `deve marcar encomenda como retirada`() {

    val delivery =
        Delivery(
            id = 1L,
            description = "Mercado Livre",
            status = DeliveryStatus.PENDING
        )

    whenever(
        repository.findById(1L)
    ).thenReturn(
        Optional.of(delivery)
    )

    doAnswer {
        it.arguments[0]
    }.whenever(repository)
        .save(any())

    val result =
        service.pickUp(1L)

    assertEquals(
        DeliveryStatus.PICKED_UP,
        result.status
    )
}

// RN22 - Não deve permitir retirada duplicada
@Test
fun `deve impedir retirada duplicada`() {

    val delivery =
        Delivery(
            id = 1L,
            description = "Shopee",
            status =
                DeliveryStatus.PICKED_UP
        )

    whenever(
        repository.findById(1L)
    ).thenReturn(
        Optional.of(delivery)
    )

    assertThrows<RuntimeException> {

        service.pickUp(1L)
    }
}


}
