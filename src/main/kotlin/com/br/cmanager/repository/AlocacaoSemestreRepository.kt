package com.br.cmanager.repository

import com.br.cmanager.entity.AlocacaoSemestre
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AlocacaoSemestreRepository: JpaRepository<AlocacaoSemestre, Long> {
}