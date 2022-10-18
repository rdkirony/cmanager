package com.br.cmanager.service

import com.br.cmanager.dto.tipomateria.TipoMateriaDto
import com.br.cmanager.entity.TipoMateria
import com.br.cmanager.exception.NotFoundException
import com.br.cmanager.repository.TipoMateriaRepository
import com.br.cmanager.transformers.TipoMateriaTransformer
import org.springframework.stereotype.Service

@Service
class TipoMateriaService(private val tipoMateriaRepository: TipoMateriaRepository, private val transformer: TipoMateriaTransformer) {

    fun buscarPorIdRetornandoMateria(id:Long):TipoMateria{
        return tipoMateriaRepository.findById(id).orElseThrow{ NotFoundException("Tipo matéria não encontrado") };
    }
    fun listarTodasMaterias(): List<TipoMateriaDto> {
        var tipoMaterias = tipoMateriaRepository.findAll();
        return tipoMaterias.map { tipoMateria: TipoMateria? -> tipoMateria?.let { transformer.tipoMateriaToTipoMateriaDto(it) }!! }
    }
}