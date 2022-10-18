package com.br.cmanager.dto.autenticacao

data class TokenDto(
    val token:String,
    val tipo:String,
    val perfilId:Long
)