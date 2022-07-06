package com.br.cmanager.repository

import com.br.cmanager.entity.Pessoa
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PessoaRepository: JpaRepository<Pessoa,Long> {
    fun findByNome(nome: String, paginacao: Pageable): Page<Pessoa>
}