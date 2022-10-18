package com.br.cmanager.repository

import com.br.cmanager.entity.Semestre
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SemestreRepository: JpaRepository<Semestre, Long> {
}