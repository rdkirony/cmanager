package com.br.cmanager.service

import com.br.cmanager.entity.Usuario
import com.br.cmanager.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class AutenticacaoService(
    private val repository: UsuarioRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val usuario = repository.findByLogin(username)
        if (usuario.isPresent) {
            return usuario.get()
        }

        throw UsernameNotFoundException("Dados inválidos!")
    }
}