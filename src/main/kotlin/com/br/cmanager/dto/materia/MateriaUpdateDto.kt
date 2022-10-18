package com.br.cmanager.dto.materia

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class MateriaUpdateDto (
    @field:NotNull(message = "Id necessário para atualização")
    val id: Long,
    @field:NotEmpty(message = "Nome não pode ser em branco")
    @field:Size(min = 2, max = 200, message = "Nome deve ter entre 2 e 200 caracteres")
    val nome: String = "",
    @field:NotEmpty(message = "Descrição não pode ser em branco")
    @field:Size(min = 2, max = 255, message = "Descrição deve ter entre 2 e 255 caracteres")
    val descricao: String = "",
    val tipoMateriaId: Long,
)