package com.condominio.backend.prestador

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PrestadorRepository : JpaRepository<Prestador, String>
