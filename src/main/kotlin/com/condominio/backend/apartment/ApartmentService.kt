package com.condominio.backend.apartment

import org.springframework.stereotype.Service

@Service
class ApartmentService(
    private val repository: ApartmentRepository
) {

    fun create(
        apartment: Apartment
    ): Apartment {

        if (
            repository.existsByBlockAndNumber(
                apartment.block,
                apartment.number
            )
        ) {
            throw RuntimeException(
                "Apartamento já cadastrado"
            )
        }

        return repository.save(apartment)
    }

    fun findAll(): List<Apartment> {
        return repository.findAll()
    }

    fun findById(
        id: Long
    ): Apartment {

        return repository.findById(id)
            .orElseThrow {
                RuntimeException("Apartamento não encontrado")
            }
    }
}