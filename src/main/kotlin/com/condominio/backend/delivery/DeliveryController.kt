package com.condominio.backend.delivery

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/deliveries")
class DeliveryController(
    private val service: DeliveryService
) {

    @PostMapping
    fun create(
        @RequestBody delivery: Delivery
    ): Delivery {

        return service.create(delivery)
    }

    @PutMapping("/{id}/pickup")
    fun pickUp(
        @PathVariable id: Long
    ): Delivery {

        return service.pickUp(id)
    }

    @GetMapping
    fun findAll(): List<Delivery> {

        return service.findAll()
    }
}