package com.ljmtecnologica.mercadolivro.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ljmtecnologica.mercadolivro.enums.ErrorEnum
import com.ljmtecnologica.mercadolivro.exception.AuthenticationException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.lang.Exception
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil
): UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        try {
            val login = jacksonObjectMapper().readValue(request.inputStream, Login::class.java)
            val authToken = UsernamePasswordAuthenticationToken(login.email, login.password)
            return authenticationManager.authenticate(authToken)
        } catch (e: Exception){
            throw AuthenticationException(ErrorEnum.ML0001.message, ErrorEnum.ML0001.internalCode)
        }
    }

    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain, authResult: Authentication) {
        val email = (authResult.principal as UserDetailsSecurity).username
        val token = this.jwtUtil.generateToken(email)
        response.addHeader("Authorization", "Bearer $token")
    }

}