package com.condominio.backend.document

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*

class DocumentServiceTest {

/*
 * MOCK:
 * Simula o comportamento do repositório
 * sem acessar o banco real.
 */

private val repository:
    DocumentRepository = mock()

private val service =
    DocumentService(repository)

// RN23 - Documento deve possuir nome
@Test
fun `deve impedir documento sem nome`() {

    val document =
        Document(
            name = "",
            fileType = "application/pdf",
            data = "teste".toByteArray()
        )

    assertThrows<RuntimeException> {

        service.create(document)
    }
}

// RN24 - Documento deve possuir arquivo
@Test
fun `deve impedir documento sem arquivo`() {

    val document =
        Document(
            name = "Regimento",
            fileType = "application/pdf",
            data = null
        )

    assertThrows<RuntimeException> {

        service.create(document)
    }
}

// RN25 - Tipo do arquivo deve ser PDF ou imagem
@Test
fun `deve impedir tipo invalido`() {

    val document =
        Document(
            name = "Arquivo",
            fileType = "application/zip",
            data = "teste".toByteArray()
        )

    assertThrows<RuntimeException> {

        service.create(document)
    }
}

// RN26 - Documento válido deve ser salvo
@Test
fun `deve salvar documento valido`() {

    val document =
        Document(
            name = "Assembleia",
            fileType = "application/pdf",
            data = "teste".toByteArray()
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
        service.create(document)

    assertEquals(
        "Assembleia",
        result.name
    )
}


}
