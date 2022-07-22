package com.br.cmanager.dto.pessoa

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size


data class PessoaCadastroDto(
    @field:NotEmpty(message = "Nome não pode ser em branco")
    @field:Size(min = 2, max = 30, message = "Nome deve ter entre 2 e 30 caracteres")
    val nome: String,
    val cpf: String,
    val email: String,
    val endereco: String,
)
