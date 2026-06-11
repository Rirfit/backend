package com.condominio.backend.visitor

import com.condominio.backend.user.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.time.LocalDateTime
import java.util.*

class VisitorServiceTest {

/*
 * MOCK:
 * Simula o comportamento do repositório
 * sem acessar o banco real.
 */

private val repository:
    VisitorRepository = mock()

private val service =
    VisitorService(repository)

// RN10 - Visitante deve possuir morador responsável
@Test
fun `deve criar visitante com sucesso`() {

    val resident =
        User(
            id = 1L,
            name = "Gabriel",
            email = "gabriel@email.com",
            password = "123456"
        )

    val visitor =
        Visitor(
            name = "Carlos",
            document = "123456789",
            resident = resident,
            validUntil =
                LocalDateTime.now()
                    .plusDays(1)
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
        service.create(visitor)

    assertEquals(
        VisitorStatus.ACTIVE,
        result.status
    )
}

// RN11 - Visitante expirado não pode entrar
@Test
fun `deve impedir visitante expirado`() {

    val resident =
        User(
            id = 1L,
            name = "Gabriel",
            email = "gabriel@email.com",
            password = "123456"
        )

    val visitor =
        Visitor(
            name = "Carlos",
            resident = resident,
            validUntil =
                LocalDateTime.now()
                    .minusDays(1)
        )

    assertThrows<RuntimeException> {

        service.create(visitor)
    }
}

// RN12 - Visitante bloqueado deve alterar status para BLOCKED
@Test
fun `deve bloquear visitante`() {

    val visitor =
        Visitor(
            id = 1L,
            name = "Carlos",
            status =
                VisitorStatus.ACTIVE
        )

    whenever(
        repository.findById(1L)
    ).thenReturn(
        Optional.of(visitor)
    )

    doAnswer {
        it.arguments[0]
    }.whenever(repository)
        .save(any())

    val result =
        service.block(1L)

    assertEquals(
        VisitorStatus.BLOCKED,
        result.status
    )
}


}
