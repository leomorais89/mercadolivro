package com.ljmtecnologica.mercadolivro.controller.request

data class PutCustomer(
    val id : Int,
    val name : String?,
    val email : String?
)