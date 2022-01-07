package com.ljmtecnologica.mercadolivro.security

import com.ljmtecnologica.mercadolivro.enums.ErrorEnum
import com.ljmtecnologica.mercadolivro.exception.AuthenticationException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthorizationFilter(
    authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil,
    private val userDetailsSecurityService: UserDetailsSecurityService
): BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val token = request.getHeader("Authorization")
        if(!token.isNullOrEmpty() && token.startsWith("Bearer ")){
            val auth = this.getAuthentication(token.split(" ")[1])
            SecurityContextHolder.getContext().authentication = auth
        }
        chain.doFilter(request, response)
    }

    private fun getAuthentication(token: String): UsernamePasswordAuthenticationToken{
        if(!this.jwtUtil.isValidToken(token)){
            throw AuthenticationException(ErrorEnum.ML0003.message, ErrorEnum.ML0003.internalCode)
        }
        val email = this.jwtUtil.getSubject(token)
        val customer = this.userDetailsSecurityService.loadUserByUsername(email)
        return UsernamePasswordAuthenticationToken(customer, null, customer.authorities)
    }

}