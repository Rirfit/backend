package com.condominio.backend.prestador

import org.springframework.stereotype.Service

@Service
class PrestadorService(
    private val repository: PrestadorRepository
) {
    fun listar(): List<Prestador> = repository.findAll()

    fun salvar(prestador: Prestador): Prestador {
        // Mantém id informado pelo cliente (frontend) para facilitar o PUT/DELETE
        // Se id vazio ou nulo, gera automaticamente (JPA) mas neste projeto o frontend manda id string.
        return repository.save(prestador)
    }

    fun excluir(id: String) {
        if (!repository.existsById(id)) return
        repository.deleteById(id)
    }

    fun buscarOuFalhar(id: String): Prestador {
        return repository.findById(id).orElseThrow {
            NoSuchElementException("Prestador nao encontrado: $id")
        }
    }
}