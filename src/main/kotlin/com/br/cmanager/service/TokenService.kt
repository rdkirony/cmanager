package com.br.cmanager.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest


@Service
class TokenService {

    @Value("\${cmanager.jwt.expiration}")
    private val expiration: String? = null

    @Value("\${cmanager.jwt.secret}")
    private val secret: String? = null

    fun gerarToken(idUsuario:Long): String {
        val hoje = Date()
        val dataExpiracao = Date(hoje.time + expiration!!.toLong())
        return Jwts.builder()
            .setIssuer("API Cmanager")
            .setSubject(idUsuario.toString())
            .setIssuedAt(hoje)
            .setExpiration(dataExpiracao)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun isTokenValido(token: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getIdUsuario(token: String): Long {
        val claims: Claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
        return claims.subject.toLong()
    }

    private fun decode(encodedString: String): String? {
        return String(Base64.getUrlDecoder().decode(encodedString))
    }

    fun parseDecodeToJson(token: String):JSONObject{
        val parts: List<String> = token.split(".")
        val payload = JSONObject(decode(parts[1]))
        if(!validarExpiryTimestamp(payload)){
           throw Exception()
        }
        return payload

    }

    private fun validarExpiryTimestamp(payload:JSONObject):Boolean{
        return payload.getLong("exp") > (System.currentTimeMillis() / 1000)
    }

    fun recuperarToken(request: HttpServletRequest): String? {
        val token = request.getHeader("Authorization")
        return if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            null
        } else token.substring(7, token.length)
    }

}