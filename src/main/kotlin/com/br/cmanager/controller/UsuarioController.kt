package com.br.cmanager.controller

import com.br.cmanager.dto.usuario.UsuarioCadastroDto
import com.br.cmanager.dto.usuario.UsuarioDto
import com.br.cmanager.dto.usuario.UsuarioUpdateDto
import com.br.cmanager.service.UsuarioService
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
@RequestMapping("/usuarios")
class UsuarioController(private val service: UsuarioService) {

    @GetMapping
    fun listar(
        @RequestParam(required = false) login: String?,
        @PageableDefault(size = 10, sort = ["login"], direction = Sort.Direction.ASC)  paginacao: Pageable
    ): Page<UsuarioDto> {
        return service.listar(login, paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): UsuarioDto {
        return service.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid usuarioCadastroDto: UsuarioCadastroDto,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<UsuarioDto> {
        val usuarioDto = service.cadastrar(usuarioCadastroDto)
        val uri = uriBuilder.path("/usuarios/${usuarioDto.id}").build().toUri()
        return ResponseEntity.created(uri).body(usuarioDto)
    }

    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid usuarioUpdateDto: UsuarioUpdateDto): ResponseEntity<UsuarioDto> {
        val usuarioDto = service.atualizar(usuarioUpdateDto)
        return ResponseEntity.ok(usuarioDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}