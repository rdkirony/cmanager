package com.br.cmanager.dto.usuario

import com.br.cmanager.dto.perfil.PerfilDto
import com.br.cmanager.dto.pessoa.PessoaDto

data class UsuarioDto(
    val id: Long?=null,
    val pessoaDto: PessoaDto,
    val perfilDto:PerfilDto,
    val login:String,
    val senha:String
)