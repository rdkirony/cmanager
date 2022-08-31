package com.br.cmanager.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table


@Table(name = "Usuario")
@Entity
class Usuario: UserDetails  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? =null
    @OneToOne
    @JoinColumn(name = "PESSOA_ID", referencedColumnName = "ID")
    var pessoa: Pessoa = Pessoa()
    @OneToOne
    @JoinColumn(name = "PERFIL_ID", referencedColumnName = "ID")
    var perfil: Perfil = Perfil()
    @field:Column(nullable = false, name = "login")
    var login:String = ""
    @field:Column(nullable = false, name = "senha")
    var senha = ""
        @JsonIgnore
        get() = field
        set(value) {

            val passwordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        var perfis: ArrayList<Perfil> = ArrayList()
        perfis.add(perfil)
        return perfis
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return login
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}

