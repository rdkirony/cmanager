package com.br.cmanager.controller

import com.br.cmanager.dto.pessoa.PessoaCadastroDto
import com.br.cmanager.dto.pessoa.PessoaUpdateDto
import com.br.cmanager.dto.pessoa.PessoaDto
import com.br.cmanager.dto.usuario.UsuarioDto
import com.br.cmanager.service.PessoaService
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
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@RequestMapping("/pessoas")
class PessoaController(private val service: PessoaService) {


    @GetMapping
    fun listar(
        @RequestParam(required = false) nome: String?,
        @RequestParam(required = false) email: String?,
        @RequestParam(required = false) cpf: String?,
        @RequestParam(required = false) endereco: String?,
        @PageableDefault(size = 10, sort = ["nome"], direction = Sort.Direction.ASC)  paginacao: Pageable
    ): Page<PessoaDto> {
        return service.listar(nome,email,cpf,endereco ,paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): PessoaDto {
        return service.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid pessoaCadastroDto: PessoaCadastroDto,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<PessoaDto> {
        val pessoaDTO = service.cadastrar(pessoaCadastroDto)
        val uri = uriBuilder.path("/pessoas/${pessoaDTO.id}").build().toUri()
        return ResponseEntity.created(uri).body(pessoaDTO)
    }

    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid pessoaAtualizarDTO: PessoaUpdateDto): ResponseEntity<PessoaDto> {
        val pessoaDTO = service.atualizar(pessoaAtualizarDTO)
        return ResponseEntity.ok(pessoaDTO)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
    @GetMapping("/token")
    fun buscarPorToken(request: HttpServletRequest):PessoaDto{
        return service.buscarPorToken(request)
    }


}