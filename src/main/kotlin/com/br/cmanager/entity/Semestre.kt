package com.br.cmanager.entity

import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "SEMESTRE")
@Entity
class Semestre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @field:Column(nullable = false, name = "data_inicial")
    var dataInicial: Date = Date()

    @field:Column(nullable = false, name = "data_final")
    var dataFinal: Date = Date()
}