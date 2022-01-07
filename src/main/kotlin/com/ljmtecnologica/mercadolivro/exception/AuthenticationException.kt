package com.ljmtecnologica.mercadolivro.exception

import org.springframework.security.core.AuthenticationException

class AuthenticationException(
    override val message: String,
    val internalCode: String
): AuthenticationException(message) {
}