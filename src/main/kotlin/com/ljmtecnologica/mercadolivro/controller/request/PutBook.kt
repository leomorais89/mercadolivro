package com.ljmtecnologica.mercadolivro.controller.request

import java.math.BigDecimal

data class PutBook(
    val id : Int,
    val name : String?,
    val price : BigDecimal?
)