package com.condominio.backend.user

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

   fun create(user: User): User {

    if (repository.existsByEmail(user.email)) {
        throw RuntimeException(
            "Email já cadastrado"
        )
    }

    if (user.apartment?.id != null) {

        val moradoresNoApartamento =
            repository.countByApartmentId(
                user.apartment!!.id!!
            )

        if (moradoresNoApartamento >= 6) {
            throw RuntimeException(
                "Limite de moradores atingido"
            )
        }
    }

    user.password =
        passwordEncoder.encode(
            user.password
        )

    return repository.save(user)
}

    fun findAll(): List<User> {
        return repository.findAll()
    }

    fun findById(id: Long): User {
        return repository.findById(id)
            .orElseThrow {
                RuntimeException("Usuário não encontrado")
            }
    }
}