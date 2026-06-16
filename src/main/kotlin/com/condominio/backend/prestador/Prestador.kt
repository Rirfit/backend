package com.condominio.backend.prestador

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Prestador(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: String? = null,

    var nome: String = "",
    var empresa: String = "",
    var telefone: String = "",
    var servico: String = "",

    // Mantemos como String para compatibilidade com o frontend (dataVisita)
    var dataVisita: String = "",

    var autorizado: Boolean = false
)