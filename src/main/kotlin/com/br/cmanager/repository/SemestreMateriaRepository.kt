package com.br.cmanager.repository

import com.br.cmanager.entity.SemestreMateria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SemestreMateriaRepository: JpaRepository<SemestreMateria,Long> {
}