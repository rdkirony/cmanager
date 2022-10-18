package com.br.cmanager.repository

import com.br.cmanager.entity.Materia
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface MateriaRepository: JpaRepository<Materia,Long> {
    fun findByNome(nome: String, paginacao: Pageable): Page<Materia>
    fun findByNomeLikeIgnoreCase(nome: String, paginacao: Pageable): Page<Materia>
}