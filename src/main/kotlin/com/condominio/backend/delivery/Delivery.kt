package com.condominio.backend.delivery

import com.condominio.backend.user.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "deliveries")
data class Delivery(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var description: String = "",

    var receivedAt: LocalDateTime =
        LocalDateTime.now(),

    var pickedUpAt: LocalDateTime? = null,

    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus =
        DeliveryStatus.PENDING,

    @ManyToOne
    @JoinColumn(name = "resident_id")
    var resident: User? = null
)