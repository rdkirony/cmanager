package com.br.cmanager.service

import com.br.cmanager.dto.perfil.PerfilCadastroDto
import com.br.cmanager.dto.perfil.PerfilDto
import com.br.cmanager.dto.perfil.PerfilUpdateDto
import com.br.cmanager.entity.Perfil
import com.br.cmanager.entity.Usuario
import com.br.cmanager.exception.NotFoundException
import com.br.cmanager.repository.PerfilRepository
import com.br.cmanager.transformers.PerfilTransformer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PerfilService(
    private  val repository: PerfilRepository,
    private val perfilTransformer: PerfilTransformer
    ) {

    fun listar(nome: String?, paginacao: Pageable): Page<PerfilDto> {
        val perfis = if(nome == null){
            repository.findAll(paginacao);
        }else{
            repository.findByNome(nome, paginacao)
        }
        return perfis.map { perfil: Perfil? -> perfil?.let { perfilTransformer.perfilToPerfilDto(it) } }
    }

    fun buscarPorId(id: Long): PerfilDto {
        val perfil = repository.findById(id).orElseThrow{ NotFoundException("Perfil não encontrado") }
        return perfilTransformer.perfilToPerfilDto(perfil)
    }

    fun cadastrar(perfilCadastroDto: PerfilCadastroDto): PerfilDto {
        val perfil = perfilTransformer.perfilCadastroToPerfilDto(perfilCadastroDto)
        repository.save(perfil)
        return perfilTransformer.perfilToPerfilDto(perfil)
    }

    fun atualizar(pessoaAtualizarDTO: PerfilUpdateDto): PerfilDto {
        val perfil = repository.findById(pessoaAtualizarDTO.id).orElseThrow{ NotFoundException("Perfil não encontrado")}
        perfil.descricao = pessoaAtualizarDTO.descricao
        perfil.nome = pessoaAtualizarDTO.nome
        repository.save(perfil)
        return perfilTransformer.perfilToPerfilDto(perfil)
    }

    fun deletar(id: Long) {
        repository.deleteById(id);
    }
    fun buscarPorIdRetornandoPerfil(id: Long):Perfil{
        return repository.findById(id).orElseThrow{ NotFoundException("Perfil não encontrado") }

    }
}