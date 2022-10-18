package com.br.cmanager.controller

import com.br.cmanager.dto.alocacao.AlocacaoCadastroDto
import com.br.cmanager.dto.alocacao.AlocacaoDto
import com.br.cmanager.dto.materia.MateriaDto
import com.br.cmanager.service.AlocacaoSemestreService
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/alocacao")
class AlocacaoSemestreController(
    private val service: AlocacaoSemestreService
) {

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid alocacaoCadastroDto: AlocacaoCadastroDto,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<AlocacaoDto> {
        val alocacaoCadastroDto = service.cadastrar(alocacaoCadastroDto)
        val uri = uriBuilder.path("/alocacao/${alocacaoCadastroDto.id}").build().toUri()
        return ResponseEntity.created(uri).body(alocacaoCadastroDto)
    }

//    @GetMapping
//    fun listar(
//        @RequestParam(required = false) nome: String?,
//        @PageableDefault(size = 27, direction = Sort.Direction.ASC) paginacao: Pageable
//    ): Page<MateriaDto> {
//        return service.listar(nome, paginacao)
//    }
//
//    @GetMapping("/{id}")
//    fun buscarPorId(@PathVariable id: Long): MateriaDto {
//        return service.buscarPorId(id)
//    }
//

//
//    @PutMapping
//    @Transactional
//    fun atualizar(@RequestBody @Valid materiaUpdateDto: MateriaUpdateDto): ResponseEntity<MateriaDto> {
//        val materiaDto = service.atualizar(materiaUpdateDto)
//        return ResponseEntity.ok(materiaDto)
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @Transactional
//    fun deletar(@PathVariable id: Long) {
//        service.deletar(id)
//
//    }
}