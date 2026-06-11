package com.condominio.backend.user

import com.condominio.backend.user.dto.CreateUserRequest
import com.condominio.backend.user.dto.UserResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    private val service: UserService
) {

    @PostMapping
    fun create(
        @RequestBody
        @Valid
        request: CreateUserRequest
    ): UserResponse {

        val user = User(
            name = request.name,
            email = request.email,
            password = request.password,
            role = request.role
        )

        val saved = service.create(user)

        return UserResponse(
            id = saved.id,
            name = saved.name,
            email = saved.email,
            role = saved.role,
            active = saved.active
        )
    }

    @GetMapping
    fun findAll(): List<UserResponse> {

        return service.findAll().map {

            UserResponse(
                id = it.id,
                name = it.name,
                email = it.email,
                role = it.role,
                active = it.active
            )
        }
    }
}