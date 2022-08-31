package com.br.cmanager.transformers

import com.br.cmanager.dto.perfil.PerfilCadastroDto
import com.br.cmanager.dto.perfil.PerfilDto
import com.br.cmanager.dto.pessoa.PessoaDto
import com.br.cmanager.entity.Perfil
import org.springframework.stereotype.Component

@Component
class PerfilTransformer {

    fun perfilToPerfilDto(t: Perfil): PerfilDto {
        return PerfilDto(
            id = t.id,
            nome = t.nome,
            descricao = t.descricao
        )
    }

    fun perfilCadastroToPerfilDto(t: PerfilCadastroDto): Perfil {
        val perfil = Perfil()
        perfil.nome = t.nome
        perfil.descricao = t.descricao
        return perfil
    }
}