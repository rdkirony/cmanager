package com.br.cmanager.repository

import com.br.cmanager.entity.Usuario
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsuarioRepository: JpaRepository<Usuario,Long> {
    fun findByLogin(login: String, paginacao: Pageable): Page<Usuario>
    fun findByLogin(login: String): Optional<Usuario>
}