package com.br.cmanager.dto.materia

import com.br.cmanager.dto.tipomateria.TipoMateriaDto

data class MateriaDto (
    val id: Long?=null,
    val nome: String = "",
    val descricao: String = "",
    val tipoMateriaDto: TipoMateriaDto
)