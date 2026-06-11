package com.condominio.backend.apartment

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/apartments")
class ApartmentController(
    private val service: ApartmentService
) {

    @PostMapping
    fun create(
        @RequestBody apartment: Apartment
    ): Apartment {

        return service.create(apartment)
    }

    @GetMapping
    fun findAll(): List<Apartment> {

        return service.findAll()
    }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: Long
    ): Apartment {

        return service.findById(id)
    }
}