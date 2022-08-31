package com.br.cmanager.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table


@Table(name = "Pessoa")
@Entity
data class Pessoa (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @field:Column(nullable = false, name = "nome")
    var nome: String = "",
    @Column(name = "cpf")
    var cpf: String = "",
    @Column(name = "email")
    var email: String = "",
    @Column(name = "endereco")
    var endereco: String = ""


)
