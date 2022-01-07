package com.ljmtecnologica.mercadolivro.event.listeners

import com.ljmtecnologica.mercadolivro.model.enums.BookStatusEnum
import com.ljmtecnologica.mercadolivro.event.PurchaseEvent
import com.ljmtecnologica.mercadolivro.service.BookService
import com.ljmtecnologica.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class PurchaseListener(
    private val purchaseService: PurchaseService,
    private val bookService: BookService
) {

    @Async
    @EventListener
    fun nfeGenerate(purchaseEvent: PurchaseEvent){
        val nfe = UUID.randomUUID().toString()
        val purchase = purchaseEvent.purchase.copy(nfe = nfe)
        this.purchaseService.update(purchase)
    }

    @Async
    @EventListener
    fun updateStatusBookVendido(purchaseEvent: PurchaseEvent){
        purchaseEvent.purchase.books.map { it.status = BookStatusEnum.VENDIDO }
        this.bookService.updateAll(purchaseEvent.purchase.books)
    }

}