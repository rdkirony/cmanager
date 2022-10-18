package com.br.cmanager.dto.semestre

import java.util.Date

data class SemestreDto (
    val id: Long?=null,
    val dataIncial : Date,
    val dataFinal : Date
)