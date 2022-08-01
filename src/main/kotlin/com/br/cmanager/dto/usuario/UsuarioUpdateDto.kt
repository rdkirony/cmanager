package com.br.cmanager.dto.usuario

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UsuarioUpdateDto(

    @field:NotNull(message = "Id necessário para atualização")
    val id: Long,
    @field:NotEmpty(message = "Senha não pode ser em branco")
    @field:Size(min = 5, max = 50, message = "Senha deve ter entre 5 e 50 caracteres")
    val senha:String
)
