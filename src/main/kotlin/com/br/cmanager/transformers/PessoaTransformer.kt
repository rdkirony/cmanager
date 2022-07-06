package com.br.cmanager.transformers

import com.br.cmanager.dto.PessoaCadastroDto
import com.br.cmanager.dto.PessoaDto
import com.br.cmanager.dto.PessoaUpdateDto
import com.br.cmanager.entity.Pessoa
import org.springframework.stereotype.Component

@Component
class PessoaTransformer {

    fun pessoaDtoToPessoa(t: PessoaDto): Pessoa {
        return Pessoa(
            nome = t.nome,
            endereco = t.endereco,
            cpf = t.cpf,
            email = t.email,
            id = t.id
        )
    }
    fun pessoaToPessoaDto(t: Pessoa): PessoaDto {
        return PessoaDto(
            email = t.email,
            cpf = t.cpf,
            endereco = t.endereco,
            nome = t.nome,
            id = t.id
        )
    }

    fun pessoaUpdateDtoToPessoa(t: PessoaUpdateDto): Pessoa {
        return Pessoa(
            nome = t.nome,
            endereco = t.endereco,
            cpf = t.cpf,
            email = t.email,
            id = t.id
        )
    }

    fun pessoaCadastroDtoToPessoa(t: PessoaCadastroDto): Pessoa {
        return Pessoa(
            nome = t.nome,
            endereco = t.endereco,
            cpf = t.cpf,
            email = t.email
        )
    }

}