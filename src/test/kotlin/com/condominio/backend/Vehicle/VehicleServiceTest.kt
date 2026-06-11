package com.condominio.backend.vehicle

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.*

class VehicleServiceTest {

/*
 * MOCK:
 * Simula o comportamento do repositório
 * sem acessar o banco real.
 */

private val repository:
    VehicleRepository = mock()

private val service =
    VehicleService(repository)

// RN07 - Veículo deve ser cadastrado com status AUTHORIZED
@Test
fun `deve cadastrar veiculo com sucesso`() {

    val vehicle =
        Vehicle(
            plate = "ABC1234",
            model = "Civic",
            color = "Preto"
        )

/*
 * STUB:
 * Simula retorno esperado para o teste.
 */

    whenever(
        repository.existsByPlate(
            vehicle.plate
        )
    ).thenReturn(false)

    doAnswer {
        it.arguments[0]
    }.whenever(repository)
        .save(any())

    val result =
        service.create(vehicle)

    assertEquals(
        VehicleStatus.AUTHORIZED,
        result.status
    )
}

// RN08 - Não deve permitir placas duplicadas
@Test
fun `deve impedir placa duplicada`() {

    val vehicle =
        Vehicle(
            plate = "ABC1234"
        )

    whenever(
        repository.existsByPlate(
            vehicle.plate
        )
    ).thenReturn(true)

    assertThrows<RuntimeException> {

        service.create(vehicle)
    }
}

// RN09 - Veículo bloqueado deve alterar status para BLOCKED
@Test
fun `deve bloquear veiculo`() {

    val vehicle =
        Vehicle(
            id = 1L,
            plate = "ABC1234",
            status =
                VehicleStatus.AUTHORIZED
        )

    whenever(
        repository.findById(1L)
    ).thenReturn(
        Optional.of(vehicle)
    )

    doAnswer {
        it.arguments[0]
    }.whenever(repository)
        .save(any())

    val result =
        service.block(1L)

    assertEquals(
        VehicleStatus.BLOCKED,
        result.status
    )
}


}
