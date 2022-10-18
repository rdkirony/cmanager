package com.br.cmanager.transformers


import com.br.cmanager.dto.materia.MateriaCadastroDto
import com.br.cmanager.dto.materia.MateriaDto
import com.br.cmanager.dto.tipomateria.TipoMateriaDto
import com.br.cmanager.entity.Materia
import com.br.cmanager.service.TipoMateriaService
import org.springframework.stereotype.Component

@Component
class MateriaTransformer(private val tipoMateriaService: TipoMateriaService) {

    fun materiaToMateriaDto(t: Materia): MateriaDto {
        return MateriaDto(
            id = t.id,
            nome = t.nome,
            descricao = t?.descricao?:"",
            tipoMateriaDto = TipoMateriaDto(
                id = t.tipoMateria.id,
                nome = t.tipoMateria.nome
            )
        )
    }

    fun materiaCadastroToMateriaDto(t: MateriaCadastroDto): Materia {
        val materia = Materia()
        materia.nome = t.nome
        materia.descricao = t.descricao
        materia.tipoMateria = tipoMateriaService.buscarPorIdRetornandoMateria(t.tipoMateriaId)
        return materia
    }
}