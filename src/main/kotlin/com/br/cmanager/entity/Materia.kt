package com.br.cmanager.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Table(name = "Materia")
@Entity
class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @field:Column(nullable = false, name = "nome")
    var nome: String = ""

    @field:Column(name = "descricao")
    var descricao: String? = ""

    @ManyToOne
    @JoinColumn(name = "TIPO_MATERIA_ID", referencedColumnName = "ID")
    var tipoMateria: TipoMateria = TipoMateria()
}