package com.ljmtecnologica.mercadolivro.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ljmtecnologica.mercadolivro.enums.ErrorEnum
import com.ljmtecnologica.mercadolivro.exception.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustonAuthenticationEntryPoint: AuthenticationEntryPoint {

    override fun commence(request: HttpServletRequest?, response: HttpServletResponse, authException: AuthenticationException) {
        response.contentType = "application/json"
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        val errorResponse = ErrorResponse(
            httpCode = HttpStatus.UNAUTHORIZED.value(),
            error = HttpStatus.UNAUTHORIZED.name,
            message = authException.message ?: ErrorEnum.ML0004.message,
            internalCode = ErrorEnum.ML0004.internalCode
        )
        response.outputStream.print(jacksonObjectMapper().writeValueAsString(errorResponse))
    }
}