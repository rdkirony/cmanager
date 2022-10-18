package com.br.cmanager.controller

import com.br.cmanager.dto.autenticacao.LoginDto
import com.br.cmanager.dto.autenticacao.TokenDto
import com.br.cmanager.exception.InvalidLoginException
import com.br.cmanager.service.TokenService
import com.br.cmanager.service.UsuarioService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
@RequestMapping("/auth")
class AutenticacaoController(
    private val usuarioService: UsuarioService,
    private val tokenService: TokenService
) {


    @PostMapping
    fun autenticar(@RequestBody form: @Valid LoginDto): ResponseEntity<TokenDto> {
        val usuarioLogin = usuarioService.validarUsuarioParaAutenticacao(form)
        return try {
            if(usuarioLogin != null) {
                val token: String = tokenService.gerarToken(usuarioLogin.id!!)
                ResponseEntity.ok(TokenDto(token, "Bearer", usuarioLogin.perfil.id!!))
            }else {
                throw InvalidLoginException("Dados inválidos")
            }

        } catch (e: AuthenticationException) {
            ResponseEntity.badRequest().build()
        }
    }
}