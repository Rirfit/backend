package com.condominio.backend.user

import com.condominio.backend.apartment.Apartment
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

class UserServiceTest {

private val repository: UserRepository = mock()

private val passwordEncoder: PasswordEncoder = mock()

private val service = UserService(
    repository,
    passwordEncoder
)

// RN01 - Usuário deve ser criado com senha criptografada
@Test
fun `deve criar usuario com sucesso`() {

    val apartment = Apartment(
        id = 1L,
        block = "A",
        number = "101"
    )

    val user = User(
        id = 1L,
        name = "Gabriel",
        email = "gabriel@email.com",
        password = "123456",
        apartment = apartment
    )

    whenever(
        repository.existsByEmail(
            user.email
        )
    ).thenReturn(false)

    whenever(
        repository.countByApartmentId(1L)
    ).thenReturn(1)

    whenever(
        passwordEncoder.encode("123456")
    ).thenReturn("senha-criptografada")

    doAnswer { invocation ->
        invocation.arguments[0]
    }.whenever(repository)
        .save(any())

    val result = service.create(user)

    assertEquals(
        "senha-criptografada",
        result.password
    )

    verify(repository).save(any())
}

// RN02 - Email não pode ser duplicado
@Test
fun `deve impedir email duplicado`() {

    val user = User(
        name = "Gabriel",
        email = "gabriel@email.com",
        password = "123456"
    )

    whenever(
        repository.existsByEmail(
            user.email
        )
    ).thenReturn(true)

    assertThrows<RuntimeException> {

        service.create(user)
    }

    verify(repository, never())
        .save(any())
}

// RN03 - Apartamento pode possuir no máximo 2 moradores
@Test
fun `deve impedir mais de dois moradores no apartamento`() {

    val apartment = Apartment(
        id = 1L,
        block = "A",
        number = "101"
    )

    val user = User(
        name = "Gabriel",
        email = "gabriel@email.com",
        password = "123456",
        apartment = apartment
    )

    whenever(
        repository.existsByEmail(
            user.email
        )
    ).thenReturn(false)

    whenever(
        repository.countByApartmentId(1L)
    ).thenReturn(2)

    assertThrows<RuntimeException> {

        service.create(user)
    }

    verify(repository, never())
        .save(any())
}

// RN04 - Usuário deve existir para busca por ID
@Test
fun `deve impedir busca de usuario inexistente`() {

    whenever(
        repository.findById(1L)
    ).thenReturn(Optional.empty())

    assertThrows<RuntimeException> {

        service.findById(1L)
    }
}


}
