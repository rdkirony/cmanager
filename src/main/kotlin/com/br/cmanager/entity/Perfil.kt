package com.br.cmanager.entity

import org.springframework.security.core.GrantedAuthority
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Perfil")
class Perfil: GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @field:Column(nullable = false, name = "nome")
    var nome: String = ""

    @field:Column(nullable = false, name = "descricao")
    var descricao: String = ""
    override fun getAuthority(): String {
        return this.nome
    }
}
