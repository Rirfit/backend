package com.condominio.backend.apartment

import jakarta.persistence.*

@Entity
@Table(name = "apartments")
data class Apartment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val block: String,

    @Column(nullable = false)
    val number: String
)