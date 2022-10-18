package com.br.cmanager.repository

import com.br.cmanager.entity.TipoMateria
import org.springframework.data.jpa.repository.JpaRepository

interface TipoMateriaRepository: JpaRepository<TipoMateria,Long> {
}