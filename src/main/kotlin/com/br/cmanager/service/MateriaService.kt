package com.br.cmanager.service


import com.br.cmanager.dto.materia.MateriaCadastroDto
import com.br.cmanager.dto.materia.MateriaDto
import com.br.cmanager.dto.materia.MateriaUpdateDto
import com.br.cmanager.entity.Materia
import com.br.cmanager.entity.TipoMateria
import com.br.cmanager.exception.NotFoundException
import com.br.cmanager.repository.MateriaRepository
import com.br.cmanager.transformers.MateriaTransformer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.Objects

@Service
class MateriaService(
    private val repository: MateriaRepository,
    private val materiaTransformer: MateriaTransformer,
    private val tipoMateriaService: TipoMateriaService
    ) {

    fun listar(nome: String?, paginacao: Pageable): Page<MateriaDto> {
        val perfis = if(nome == null){
            repository.findAll(paginacao);
        }else{
            repository.findByNomeLikeIgnoreCase("${validarCampoNull(nome)}", paginacao)
        }
        return perfis.map { materia: Materia? -> materia?.let { materiaTransformer.materiaToMateriaDto(it) } }
    }
    fun validarCampoNull(campo:String?):String{
        if(campo == null || campo == ""){
            return "%%"
        }
        return "%${campo}%"
    }

    fun buscarPorId(id: Long): MateriaDto {
        val materia = repository.findById(id).orElseThrow{ NotFoundException("Matéria não encontrada") }
        return materiaTransformer.materiaToMateriaDto(materia)
    }

    fun cadastrar(materiaCadastroDto: MateriaCadastroDto): MateriaDto {
        val materia = materiaTransformer.materiaCadastroToMateriaDto(materiaCadastroDto)
        repository.save(materia)
        return materiaTransformer.materiaToMateriaDto(materia)
    }

    fun atualizar(materiaUpdateDto: MateriaUpdateDto): MateriaDto {
        val materia = repository.findById(materiaUpdateDto.id).orElseThrow{ NotFoundException("Matéria não encontrada") }
        val tipoMateria = tipoMateriaService.buscarPorIdRetornandoMateria(materiaUpdateDto.tipoMateriaId)
        materia.descricao = materiaUpdateDto.descricao
        materia.nome = materiaUpdateDto.nome
        if(!Objects.equals(tipoMateria,materia.tipoMateria)){
            materia.tipoMateria = tipoMateria
        }
        repository.save(materia)
        return materiaTransformer.materiaToMateriaDto(materia)
    }

    fun deletar(id: Long) {
        repository.deleteById(id);
    }


}