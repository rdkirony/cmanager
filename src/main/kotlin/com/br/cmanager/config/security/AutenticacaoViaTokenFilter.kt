package com.br.cmanager.config.security

import com.br.cmanager.entity.Usuario
import com.br.cmanager.repository.UsuarioRepository
import com.br.cmanager.service.TokenService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AutenticacaoViaTokenFilter(
    private  val tokenService: TokenService,
    private val repository: UsuarioRepository
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val token = tokenService.recuperarToken(request)
        val valido = tokenService.isTokenValido(token)
        if (valido) {
            autenticarCliente(token!!)
        }

        filterChain.doFilter(request, response)
    }

    private fun autenticarCliente(token: String) {
        val idUsuario: Long = tokenService.getIdUsuario(token)
        val usuario: Usuario = repository.findById(idUsuario).get()
        val authentication = UsernamePasswordAuthenticationToken(usuario, null, usuario.authorities)
        SecurityContextHolder.getContext().authentication = authentication
    }


}