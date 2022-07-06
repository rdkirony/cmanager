package com.br.cmanager.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table


@Table(name = "Usuario")
@Entity
data class Usuario (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? =null
)

