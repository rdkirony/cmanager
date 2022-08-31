package com.br.cmanager.transformers

import com.br.cmanager.dto.perfil.PerfilDto
import com.br.cmanager.dto.pessoa.PessoaDto
import com.br.cmanager.dto.usuario.UsuarioCadastroDto
import com.br.cmanager.dto.usuario.UsuarioDto
import com.br.cmanager.entity.Usuario
import com.br.cmanager.service.PerfilService
import com.br.cmanager.service.PessoaService
import org.springframework.stereotype.Component

@Component
class UsuarioTransformer(
    private val pessoaService: PessoaService,
    private val perfilService: PerfilService
) {

    fun usuarioToUsuarioDto(t:Usuario):UsuarioDto{
        return UsuarioDto(
            id = t.id,
            login = t.login,
            perfilDto = PerfilDto(
                id = t.perfil.id,
                descricao = t.perfil.descricao,
                nome = t.perfil.nome
            ),
            pessoaDto = PessoaDto(
                id = t.pessoa.id,
                nome = t.pessoa.nome,
                endereco = t.pessoa.endereco,
                cpf = t.pessoa.cpf,
                email = t.pessoa.email
            )
        )
    }
    fun usuarioCadastroDtoToUsuario(t:UsuarioCadastroDto):Usuario{
        val usuario =  Usuario()
        usuario.senha = t.senha
        usuario.login = t.login
        usuario.perfil = perfilService.buscarPorIdRetornandoPerfil(t.perfilId)
        usuario.pessoa = pessoaService.buscarPorIdRetornandoPessoa(t.pessoaId)
        return usuario
    }
}