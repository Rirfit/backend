package com.condominio.backend.user

import com.condominio.backend.apartment.Apartment
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @field:NotBlank
    var name: String = "",

    @field:Email
    @field:NotBlank
    @Column(unique = true)
    var email: String = "",

    @field:NotBlank
    var telefone: String = "",

    @field:NotBlank
    var password: String = "",

    @Enumerated(EnumType.STRING)
    var role: Role = Role.RESIDENT,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    var apartment: Apartment? = null,

    var active: Boolean = true
)