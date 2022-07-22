package com.br.cmanager.controller

import com.br.cmanager.dto.perfil.PerfilCadastroDto
import com.br.cmanager.dto.perfil.PerfilDto
import com.br.cmanager.dto.perfil.PerfilUpdateDto
import com.br.cmanager.service.PerfilService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/perfis")
class PerfilController(var service: PerfilService) {

    @GetMapping
    fun listar(
        @RequestParam(required = false) nome: String?,
        @PageableDefault(size = 10, sort = ["nome"], direction = Sort.Direction.ASC)  paginacao: Pageable
    ): Page<PerfilDto> {
        return service.listar(nome, paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): PerfilDto {
        return service.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid pessoaCadastroDto: PerfilCadastroDto,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<PerfilDto> {
        val pessoaDTO = service.cadastrar(pessoaCadastroDto)
        val uri = uriBuilder.path("/pessoas/${pessoaDTO.id}").build().toUri()
        return ResponseEntity.created(uri).body(pessoaDTO)
    }

    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid pessoaAtualizarDTO: PerfilUpdateDto): ResponseEntity<PerfilDto> {
        val pessoaDTO = service.atualizar(pessoaAtualizarDTO)
        return ResponseEntity.ok(pessoaDTO)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }

}