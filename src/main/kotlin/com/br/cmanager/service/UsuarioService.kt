package com.br.cmanager.service

import com.br.cmanager.dto.autenticacao.LoginDto
import com.br.cmanager.dto.usuario.UsuarioCadastroDto
import com.br.cmanager.dto.usuario.UsuarioDto
import com.br.cmanager.dto.usuario.UsuarioUpdateSenhaDto
import com.br.cmanager.entity.Usuario
import com.br.cmanager.exception.NotFoundException
import com.br.cmanager.repository.UsuarioRepository
import com.br.cmanager.transformers.UsuarioTransformer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class UsuarioService(
    private val repository: UsuarioRepository,
    private val usuarioTransformer: UsuarioTransformer,
    private val tokenService: TokenService
    ) {
    fun listar(login: String?, paginacao: Pageable): Page<UsuarioDto> {
        val usuarios = if(login == null){
            repository.findAll(paginacao);
        }else{
            repository.findByLogin(login, paginacao)
        }
        return usuarios.map { usuario: Usuario? -> usuario?.let { usuarioTransformer.usuarioToUsuarioDto(it) } }
    }

    fun buscarPorId(id: Long): UsuarioDto {
        val usuario = repository.findById(id).orElseThrow{ NotFoundException("Usuário não encontrado") }
        return usuarioTransformer.usuarioToUsuarioDto(usuario);
    }

    fun cadastrar(usuarioCadastroDto: UsuarioCadastroDto): UsuarioDto {
        val usuario = usuarioTransformer.usuarioCadastroDtoToUsuario(usuarioCadastroDto)
        repository.save(usuario)
        return usuarioTransformer.usuarioToUsuarioDto(usuario)
    }

    fun atualizar(usuarioUpdateDto: UsuarioUpdateSenhaDto): UsuarioDto? {
        val usuario = repository.findById(usuarioUpdateDto.id).orElseThrow{ NotFoundException("Usuário não encontrado") }
        usuario.senha = usuarioUpdateDto.senha
        repository.save(usuario)
        return usuarioTransformer.usuarioToUsuarioDto(usuario)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun buscarPorLogin(login:String): Optional<Usuario> {
        return repository.findByLogin(login)
    }

    fun validarSenhas(senhaInserida:String, senhaUsuario:String):Boolean{
        val passwordEncoder = BCryptPasswordEncoder()
        return passwordEncoder.matches(senhaInserida,senhaUsuario)
    }

    fun validarUsuarioParaAutenticacao(loginDto:LoginDto): Long? {
        val usuario = this.buscarPorLogin(loginDto.login)
        if(usuario.isPresent) {
            if (this.validarSenhas(loginDto.senha, usuario.get().senha)) {
                return usuario.get().id
            }
        }
        return null
    }

    fun buscarPorToken(request: HttpServletRequest): UsuarioDto {
        val token = tokenService.recuperarToken(request)
        val payload = tokenService.parseDecodeToJson(token!!)
        val usuario = repository.findById(payload.getLong("sub"))
            .orElseThrow{NotFoundException("Usuário não encontrado")}
        return usuarioTransformer.usuarioToUsuarioDto(usuario)
    }

}