package com.condominio.backend.prestador

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/prestadores")
class PrestadorController(
    private val service: PrestadorService
) {

    @GetMapping
    fun listar(): List<Prestador> = service.listar()

    @PostMapping
    fun criar(@RequestBody prestador: Prestador): ResponseEntity<Prestador> {
        val saved = service.salvar(prestador)
        return ResponseEntity.ok(saved)
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: String, @RequestBody prestador: Prestador): ResponseEntity<Prestador> {
        val existing = service.buscarOuFalhar(id)
        existing.nome = prestador.nome
        existing.empresa = prestador.empresa
        existing.telefone = prestador.telefone
        existing.servico = prestador.servico
        existing.dataVisita = prestador.dataVisita
        existing.autorizado = prestador.autorizado

        val saved = service.salvar(existing)
        return ResponseEntity.ok(saved)
    }

    @DeleteMapping("/{id}")
    fun excluir(@PathVariable id: String): ResponseEntity<Void> {
        service.excluir(id)
        return ResponseEntity.noContent().build()
    }
}