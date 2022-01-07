package com.ljmtecnologica.mercadolivro.exception

import java.lang.Exception

class BadRequestException(
    override val message: String,
    val internalCode: String
): Exception() {
}