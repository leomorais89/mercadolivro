package com.ljmtecnologica.mercadolivro.exception

import java.lang.Exception

class NotFoundException(
    override val message : String,
    val internalCode : String
) : Exception() {
}