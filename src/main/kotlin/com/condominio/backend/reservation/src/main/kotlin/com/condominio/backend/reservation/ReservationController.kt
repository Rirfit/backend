package com.condominio.backend.reservation

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/reservations")
class ReservationController(
    private val service: ReservationService
) {

    @PostMapping
    fun create(
        @RequestBody reservation: Reservation
    ): Reservation {

        return service.create(
            reservation
        )
    }

    @GetMapping
    fun findAll(): List<Reservation> {

        return service.findAll()
    }

    @PutMapping("/{id}/approve")
    fun approve(
        @PathVariable id: Long
    ): Reservation {

        return service.approve(id)
    }

    @PutMapping("/{id}/reject")
    fun reject(
        @PathVariable id: Long
    ): Reservation {

        return service.reject(id)
    }
}