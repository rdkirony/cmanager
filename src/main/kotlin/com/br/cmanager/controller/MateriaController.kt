package com.br.cmanager.controller

import com.br.cmanager.dto.materia.MateriaCadastroDto
import com.br.cmanager.dto.materia.MateriaDto
import com.br.cmanager.dto.materia.MateriaUpdateDto
import com.br.cmanager.dto.tipomateria.TipoMateriaDto

import com.br.cmanager.service.MateriaService
import com.br.cmanager.service.TipoMateriaService
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
@RequestMapping("/materias")
class MateriaController(
    private val service:MateriaService,
    private val tipoMateriaService: TipoMateriaService
    ) {

    @GetMapping
    fun listar(
        @RequestParam(required = false) nome: String?,
        @PageableDefault(size = 27, sort = ["nome"], direction = Sort.Direction.ASC) paginacao: Pageable
    ): Page<MateriaDto> {
        return service.listar(nome, paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): MateriaDto {
        return service.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid materiaCadastroDto: MateriaCadastroDto,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<MateriaDto> {
        val pessoaDTO = service.cadastrar(materiaCadastroDto)
        val uri = uriBuilder.path("/materias/${pessoaDTO.id}").build().toUri()
        return ResponseEntity.created(uri).body(pessoaDTO)
    }

    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid materiaUpdateDto: MateriaUpdateDto): ResponseEntity<MateriaDto> {
        val materiaDto = service.atualizar(materiaUpdateDto)
        return ResponseEntity.ok(materiaDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)

    }
    @GetMapping("/tipoMaterias")
    fun listarTipoMaterias():List<TipoMateriaDto>{
        return tipoMateriaService.listarTodasMaterias();
    }
}