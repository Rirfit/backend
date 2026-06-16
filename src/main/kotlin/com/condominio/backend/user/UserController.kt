package com.condominio.backend.user

import com.condominio.backend.apartment.ApartmentService
import com.condominio.backend.user.dto.CreateUserRequest
import com.condominio.backend.user.dto.UserResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    private val service: UserService,
    private val apartmentService: ApartmentService
) {

    @PostMapping
    fun create(
        @RequestBody
        @Valid
        request: CreateUserRequest
    ): UserResponse {

        val apartment = apartmentService.findById(request.apartmentId)

        val user = User(
            name = request.name,
            email = request.email,
            telefone = request.telefone,
            password = request.password,
            role = request.role,
            apartment = apartment
        )

        val saved = service.create(user)

        return UserResponse(
            id = saved.id,
            name = saved.name,
            email = saved.email,
            role = saved.role,
            active = saved.active,
            telefone = saved.telefone,
            block = saved.apartment?.block.orEmpty(),
            number = saved.apartment?.number.orEmpty()
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
                active = it.active,
                telefone = it.telefone,
                block = it.apartment?.block.orEmpty(),
                number = it.apartment?.number.orEmpty()
            )
        }
    }
}
