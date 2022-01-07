package com.ljmtecnologica.mercadolivro.extension

import com.ljmtecnologica.mercadolivro.controller.request.PostBook
import com.ljmtecnologica.mercadolivro.controller.request.PostCustomer
import com.ljmtecnologica.mercadolivro.controller.request.PutBook
import com.ljmtecnologica.mercadolivro.controller.request.PutCustomer
import com.ljmtecnologica.mercadolivro.enums.BookStatusEnum
import com.ljmtecnologica.mercadolivro.enums.CustomerStatusEnum
import com.ljmtecnologica.mercadolivro.model.Book
import com.ljmtecnologica.mercadolivro.model.Customer

fun PostCustomer.toCustomer() : Customer{
    return Customer(
        name = this.name,
        email = this.email,
        status = CustomerStatusEnum.ATIVO,
        password = this.password
    )
}

fun PutCustomer.toCustomer(oldCustomer : Customer) : Customer{
    return Customer(
        id = oldCustomer.id,
        name = this.name ?: oldCustomer.name,
        email = this.email ?: oldCustomer.email,
        status = oldCustomer.status,
        password = oldCustomer.password
    )
}

fun PostBook.toBook(customer: Customer) : Book{
    return Book(
        name = this.name,
        price = this.price,
        status = BookStatusEnum.ATIVO,
        customer = customer
    )
}

fun PutBook.toBook(oldBook : Book) : Book{
    return Book(
        id = oldBook.id,
        name = this.name ?: oldBook.name,
        price = this.price ?: oldBook.price,
        status = oldBook.status,
        customer = oldBook.customer
    )
}