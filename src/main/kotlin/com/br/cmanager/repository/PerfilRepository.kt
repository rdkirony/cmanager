package com.br.cmanager.repository

import com.br.cmanager.entity.Perfil
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PerfilRepository: JpaRepository<Perfil,Long> {
    fun findByNome(nome: String, paginacao: Pageable): Page<Perfil>
}