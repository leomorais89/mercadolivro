package com.ljmtecnologica.mercadolivro.model.builder

import com.ljmtecnologica.mercadolivro.controller.request.PostPurchase
import com.ljmtecnologica.mercadolivro.model.Purchase
import com.ljmtecnologica.mercadolivro.service.BookService
import com.ljmtecnologica.mercadolivro.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseBuilder(
    private val customerService: CustomerService,
    private val bookService: BookService
) {

    fun builder(postPurchase: PostPurchase): Purchase{
        val customer = this.customerService.findById(postPurchase.customerId)
        val books = this.bookService.findAllByIds(postPurchase.booksIds)

        return Purchase(
            price = books.sumOf { it.price },
            customer = customer,
            books = books.toMutableList()
        )
    }

}