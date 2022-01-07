package com.ljmtecnologica.mercadolivro.mapper

import com.ljmtecnologica.mercadolivro.controller.request.PostPurchase
import com.ljmtecnologica.mercadolivro.model.Purchase
import com.ljmtecnologica.mercadolivro.service.BookService
import com.ljmtecnologica.mercadolivro.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
    private val customerService: CustomerService,
    private val bookService: BookService
) {

    fun toPurchase(postPurchase: PostPurchase): Purchase{
        val customer = this.customerService.findById(postPurchase.customerId)
        val books = this.bookService.findAllByIds(postPurchase.booksIds)

        return Purchase(
            price = books.sumOf { it.price },
            customer = customer,
            books = books.toMutableList()
        )
    }

}