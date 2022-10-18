package com.br.cmanager.dto.alocacao

import com.br.cmanager.dto.materia.MateriaCadastroDto
import com.br.cmanager.dto.semestre.SemestreCadastroDto

data class AlocacaoDto (
    var id:Long,
    var semestre: SemestreCadastroDto,
    val materias: List<MateriaCadastroDto>,
    val alocacao : AlocacaoDataDto
)