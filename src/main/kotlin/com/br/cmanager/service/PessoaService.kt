package com.br.cmanager.service

import com.br.cmanager.dto.pessoa.PessoaCadastroDto
import com.br.cmanager.dto.pessoa.PessoaDto
import com.br.cmanager.dto.pessoa.PessoaUpdateDto
import com.br.cmanager.entity.Pessoa
import com.br.cmanager.exception.InvalidEmailException
import com.br.cmanager.exception.NotFoundException
import com.br.cmanager.repository.PessoaRepository
import com.br.cmanager.repository.UsuarioRepository
import com.br.cmanager.transformers.PessoaTransformer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class PessoaService(
    private val repository: PessoaRepository,
    private val pessoaTransformer: PessoaTransformer,
    private val tokenService: TokenService,
    private val usuarioRepository: UsuarioRepository
) {

    fun listar(
        nome: String?,
        email: String?,
        cpf: String?,
        endereco: String?,
        paginacao:Pageable
    ): Page<PessoaDto> {
        val pessoas = if(nome == null && email == null && cpf ==null && endereco == null){
            repository.findAll(paginacao);
        }else{

            repository.findByNomeLikeIgnoreCaseAndCpfLikeIgnoreCaseAndEmailLikeIgnoreCaseAndEnderecoLikeIgnoreCase(
                "${validarCampoNull(nome)}",
                "${validarCampoNull(cpf)}",
                "${validarCampoNull(email)}",
                "${validarCampoNull(endereco)}",
                paginacao
            )
        }
        return pessoas.map { pessoa: Pessoa? -> pessoa?.let { pessoaTransformer.pessoaToPessoaDto(it) } }

    }

    fun validarCampoNull(campo:String?):String{
        if(campo == null || campo == ""){
            return "%%"
        }
        return "%${campo}%"
    }

    fun buscarPorId(id: Long): PessoaDto {
        val pessoa = repository.findById(id)
            .orElseThrow{ NotFoundException("Pessoa não encontrada") }
        return pessoaTransformer.pessoaToPessoaDto(pessoa)
    }

    fun cadastrar(pessoaCadastroDto: PessoaCadastroDto): PessoaDto {
        val pessoa = pessoaTransformer.pessoaCadastroDtoToPessoa(pessoaCadastroDto)
        if(validarEmail(pessoaCadastroDto.email)){
            repository.save(pessoa)
            return pessoaTransformer.pessoaToPessoaDto(pessoa)
        }else{
            throw InvalidEmailException("Email inválido")
        }
    }



    fun validarEmail(email:String): Boolean{
        if(email.isNotBlank() && email != null){
            return validarPatternEmail(email)
        }
        return true
    }

    fun validarPatternEmail(email: String): Boolean{
        val pattern = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
        return pattern.matches(email)
    }

    fun atualizar(pessoaAtualizarDTO: PessoaUpdateDto): PessoaDto {
        val pessoa = repository.findById(pessoaAtualizarDTO.id)
            .orElseThrow{NotFoundException("Pessoa não encontrada")}
        pessoa.cpf = pessoaAtualizarDTO.cpf
        pessoa.email = pessoaAtualizarDTO.email
        pessoa.nome = pessoaAtualizarDTO.nome
        pessoa.endereco = pessoaAtualizarDTO.endereco
        repository.save(pessoa)
        return pessoaTransformer.pessoaToPessoaDto(pessoa)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun buscarPorIdRetornandoPessoa(id: Long):Pessoa{
        return  repository.findById(id).orElseThrow{ NotFoundException("Pessoa não encontrada") }
    }

    fun buscarPorToken(request: HttpServletRequest): PessoaDto {
        val token = tokenService.recuperarToken(request)
        val payload = tokenService.parseDecodeToJson(token!!)
        val usuario = usuarioRepository.findById(payload.getLong("sub"))
            .orElseThrow{NotFoundException("Pessoa não encontrada para o Usuário informado")}
        return pessoaTransformer.pessoaToPessoaDto(usuario.pessoa)
    }


}