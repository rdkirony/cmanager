package com.br.cmanager.config.security

import com.br.cmanager.repository.UsuarioRepository
import com.br.cmanager.service.AutenticacaoService
import com.br.cmanager.service.TokenService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsUtils


@EnableWebSecurity
@Configuration
class SecurityConfigurations(
    private  val autenticacaoService: AutenticacaoService,
    private val tokenService: TokenService,
    private val usuarioRepository: UsuarioRepository
): WebSecurityConfigurerAdapter() {


    @Bean
    @Throws(Exception::class)
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }

    //Configuracoes de autenticacao
    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<UserDetailsService>(autenticacaoService).passwordEncoder(BCryptPasswordEncoder())
    }

    //Configuracoes de autorizacao
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers(HttpMethod.POST, "/auth").permitAll()
            .antMatchers(HttpMethod.POST, "/usuarios").permitAll()
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
            .anyRequest().authenticated()
            .and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().addFilterBefore(
                AutenticacaoViaTokenFilter(tokenService, usuarioRepository),
                UsernamePasswordAuthenticationFilter::class.java
            )
    }
}