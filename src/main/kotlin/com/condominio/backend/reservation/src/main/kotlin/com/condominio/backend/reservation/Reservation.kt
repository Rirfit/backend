package com.condominio.backend.reservation

import com.condominio.backend.user.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "reservations")
data class Reservation(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var spaceName: String = "",

    var reservationDate: LocalDateTime? = null,

    @Enumerated(EnumType.STRING)
    var status: ReservationStatus =
        ReservationStatus.PENDING,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var resident: User? = null
)