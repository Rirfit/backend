package com.condominio.backend.delivery

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class DeliveryService(
    private val repository: DeliveryRepository
) {

    fun create(
        delivery: Delivery
    ): Delivery {

        delivery.status =
            DeliveryStatus.PENDING

        delivery.receivedAt =
            LocalDateTime.now()

        return repository.save(delivery)
    }

    fun pickUp(
        id: Long
    ): Delivery {

        val delivery =
            repository.findById(id)
                .orElseThrow {
                    RuntimeException(
                        "Encomenda não encontrada"
                    )
                }

        if (
            delivery.status ==
            DeliveryStatus.PICKED_UP
        ) {
            throw RuntimeException(
                "Encomenda já retirada"
            )
        }

        delivery.status =
            DeliveryStatus.PICKED_UP

        delivery.pickedUpAt =
            LocalDateTime.now()

        return repository.save(delivery)
    }

    fun findAll(): List<Delivery> {
        return repository.findAll()
    }
}