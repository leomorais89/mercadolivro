package com.ljmtecnologica.mercadolivro.exception

data class ErrorResponse(
    val httpCode : Int,
    val error : String,
    val message : String,
    val internalCode : String? = null
)