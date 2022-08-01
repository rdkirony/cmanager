package com.br.cmanager.dto.perfil

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class PerfilUpdateDto(

    @field:NotNull(message = "Id necessário para atualização")
    val id: Long,
    @field:NotEmpty(message = "Nome não pode ser em branco")
    @field:Size(min = 2, max = 30, message = "Nome deve ter entre 2 e 30 caracteres")
    val nome: String = "",
    @field:NotEmpty(message = "Descrição não pode ser em branco")
    @field:Size(min = 2, max = 200, message = "Descrição deve ter entre 2 e 200 caracteres")
    val descricao: String = "",
)
