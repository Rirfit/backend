package com.condominio.backend.apartment

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*

class ApartmentServiceTest {


/*
 * MOCK:
 * Simula o comportamento do repositório
 * sem acessar o banco real.
 */


private val repository: ApartmentRepository = mock()

private val service = ApartmentService(
    repository
)

// RN01 - Apartamento deve ser criado com bloco e número válidos
@Test
fun `deve criar apartamento com sucesso`() {

    val apartment = Apartment(
        id = 1L,
        block = "A",
        number = "101"
    )

/*
 * STUB:
 * Simula retorno esperado para o teste.
 */

    whenever(
        repository.existsByBlockAndNumber(
            "A",
            "101"
        )
    ).thenReturn(false)

    doAnswer {
        it.arguments[0]
    }.whenever(repository).save(any())

    val result = service.create(apartment)

    assertEquals(
        "A",
        result.block
    )

/*
 * VERIFY:
 * Verifica se o método esperado foi executado.
 */

    verify(repository).save(any())
}

// RN02 - Não deve permitir apartamentos duplicados
@Test
fun `deve impedir apartamento duplicado`() {

    val apartment = Apartment(
        block = "A",
        number = "101"
    )

    whenever(
        repository.existsByBlockAndNumber(
            "A",
            "101"
        )
    ).thenReturn(true)

    assertThrows<RuntimeException> {

        service.create(apartment)
    }

    verify(repository, never())
        .save(any())
}

}
