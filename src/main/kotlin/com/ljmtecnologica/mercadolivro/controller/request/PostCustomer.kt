package com.ljmtecnologica.mercadolivro.controller.request

import com.ljmtecnologica.mercadolivro.annotations.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class PostCustomer(

    @field:NotBlank(message = "Name is mandatory")
    val name : String,

    @EmailAvailable
    @field:Email(message = "Invalid email")
    @field:NotBlank(message = "Email is mandatory")
    val email : String,

    @field:NotBlank(message = "Password is mandatory")
    val password: String
)