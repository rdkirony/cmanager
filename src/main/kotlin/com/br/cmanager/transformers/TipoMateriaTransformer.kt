package com.br.cmanager.transformers

import com.br.cmanager.dto.tipomateria.TipoMateriaDto
import com.br.cmanager.entity.TipoMateria
import org.springframework.stereotype.Component

@Component
class TipoMateriaTransformer {

    fun tipoMateriaToTipoMateriaDto(t: TipoMateria): TipoMateriaDto {
        return TipoMateriaDto(
            id = t.id,
            nome = t.nome
        )
    }

}