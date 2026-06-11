package com.condominio.backend.notice

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*

class NoticeServiceTest {

    /*
 * MOCK:
 * Simula o comportamento do repositório
 * sem acessar o banco real.
 */


private val repository:
    NoticeRepository = mock()

private val service =
    NoticeService(repository)

// RN17 - Aviso deve possuir título
@Test
fun `deve impedir aviso sem titulo`() {

    val notice =
        Notice(
            title = "",
            content = "Conteúdo"
        )

    assertThrows<RuntimeException> {

        service.create(notice)
    }
}

// RN18 - Aviso deve possuir conteúdo
@Test
fun `deve impedir aviso sem conteudo`() {

    val notice =
        Notice(
            title = "Aviso",
            content = ""
        )

    assertThrows<RuntimeException> {

        service.create(notice)
    }
}

// RN19 - Avisos devem possuir data automática
@Test
fun `deve criar aviso com sucesso`() {

    val notice =
        Notice(
            title = "Piscina interditada",
            content = "Manutenção"
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
        service.create(notice)

    assertEquals(
        "Piscina interditada",
        result.title
    )
}


}
