package com.br.cmanager.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany
import javax.persistence.OneToOne
import javax.persistence.Table
import kotlin.collections.ArrayList

@Table(name = "ALOCACAO_SEMESTRE")
@Entity
class AlocacaoSemestre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @field:Column(nullable = false, name = "data_inicial")
    var dataInicial: Date = Date()

    @field:Column(nullable = false, name = "data_final")
    var dataFinal: Date = Date()

    @OneToOne
    @JoinColumn(name = "MATERIA_ID", referencedColumnName = "ID")
    var semestre: Semestre = Semestre()

}