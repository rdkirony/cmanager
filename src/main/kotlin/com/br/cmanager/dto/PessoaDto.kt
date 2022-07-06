package com.br.cmanager.dto

data class PessoaDto(
    val id: Long?=null,
    val nome: String = "",
    val cpf: String,
    val email: String,
    val endereco: String,

)