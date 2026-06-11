package com.condominio.backend.delivery

import org.springframework.data.jpa.repository.JpaRepository

interface DeliveryRepository :
    JpaRepository<Delivery, Long>