package com.br.cmanager.service

import com.br.cmanager.dto.PessoaCadastroDto
import com.br.cmanager.dto.PessoaUpdateDto
import com.br.cmanager.dto.PessoaDto
import com.br.cmanager.entity.Pessoa
import com.br.cmanager.repository.PessoaRepository
import com.br.cmanager.transformers.PessoaTransformer
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PessoaService(
    private val repository: PessoaRepository,
    private val pessoaTransformer: PessoaTransformer,
) {

    fun listar(
        nome: String?,
        paginacao:Pageable
    ): Page<PessoaDto> {
        val pessoas = if(nome == null){
            repository.findAll(paginacao);
        }else{
            repository.findByNome(nome, paginacao)
        }
        return pessoas.map { pessoa: Pessoa? -> pessoa?.let { pessoaTransformer.pessoaToPessoaDto(it) } }

    }

    fun buscarPorId(id: Long): PessoaDto {
        val pessoa = repository.findById(id)
            .orElseThrow{NotFoundException()}
        return pessoaTransformer.pessoaToPessoaDto(pessoa)
    }

    fun cadastrar(pessoaCadastroDto: PessoaCadastroDto): PessoaDto {
        val pessoa = pessoaTransformer.pessoaCadastroDtoToPessoa(pessoaCadastroDto)
        repository.save(pessoa)
        return pessoaTransformer.pessoaToPessoaDto(pessoa)
    }

    fun atualizar(pessoaAtualizarDTO: PessoaUpdateDto): PessoaDto {
        val pessoa = repository.findById(pessoaAtualizarDTO.id)
            .orElseThrow{NotFoundException()}
        pessoa.cpf = pessoaAtualizarDTO.cpf
        pessoa.email = pessoaAtualizarDTO.email
        pessoa.nome = pessoaAtualizarDTO.nome
        pessoa.endereco = pessoaAtualizarDTO.endereco
        repository.save(pessoa)
        return pessoaTransformer.pessoaToPessoaDto(pessoa)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }



}