package com.ljmtecnologica.mercadolivro.controller.request

import com.ljmtecnologica.mercadolivro.annotations.BookStatusAvailable
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class PostPurchase(

    @field:Positive(message = "Value not positive")
    @field:NotNull(message = "Customer is mandatory")
    val customerId: Int,

    @field:BookStatusAvailable
    @field:NotEmpty(message = "Books is mandatory")
    val booksIds: Set<Int>
)