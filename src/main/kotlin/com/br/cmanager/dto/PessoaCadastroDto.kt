package com.br.cmanager.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size


data class PessoaCadastroDto(
    @field:NotEmpty(message = "Nome não pode ser em branco")
    @field:Size(min = 2, max = 255, message = "Nome deve ter entre 2 e 255 caracteres")
    val nome: String,
    val cpf: String,
    val email: String,
    val endereco: String,
)
