package com.br.cmanager.dto.usuario

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UsuarioCadastroDto(
    @field:NotNull(message = "Necessário informar a pessoa para cadastrar um usuário")
    val pessoaId: Long,
    @field:NotNull(message = "Necessário informar o perfil para cadastrar um usuário")
    val perfilId: Long,
    @field:NotEmpty(message = "Login não pode ser em branco")
    @field:Size(min = 3, max = 30, message = "Login deve ter entre 3 e 30 caracteres")
    val login:String,
    @field:NotEmpty(message = "Senha não pode ser em branco")
    @field:Size(min = 5, max = 50, message = "Senha deve ter entre 5 e 50 caracteres")
    val senha:String
)
