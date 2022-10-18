package com.br.cmanager.dto.materia

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class MateriaCadastroDto (
    @field:NotEmpty(message = "Nome não pode ser em branco")
    @field:Size(min = 2, max = 200, message = "Nome deve ter entre 200 e  caracteres")
    val nome: String = "",
    @field:NotEmpty(message = "Descrição não pode ser em branco")
    @field:Size(min = 2, max = 255, message = "Descrição deve ter entre 2 e 255 caracteres")
    val descricao: String = "",
    @field:NotNull(message = "Necessário informar o tipo da matéria para cadastrar uma matéria")
    val tipoMateriaId: Long,
)