package com.ljmtecnologica.mercadolivro.controller.request

import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class PostBook(

    @field:NotBlank(message = "Name is mandatory")
    val name : String,

    @field:NotNull(message = "Price is mandatory")
    val price : BigDecimal,

    @field:NotNull(message = "Customer is mandatory")
    val customerId : Int
)