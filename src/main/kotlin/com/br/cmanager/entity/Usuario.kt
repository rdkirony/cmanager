package com.br.cmanager.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table


@Table(name = "Usuario")
@Entity
data class Usuario (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? =null,
    @OneToOne
    @JoinColumn(name = "PESSOA_ID", referencedColumnName = "ID")
    var pessoa: Pessoa,
    @OneToOne
    @JoinColumn(name = "PERFIL_ID", referencedColumnName = "ID")
    var perfil: Perfil,
    @field:Column(nullable = false, name = "login")
    var login:String,
    @field:Column(nullable = false, name = "senha")
    var senha:String
)

