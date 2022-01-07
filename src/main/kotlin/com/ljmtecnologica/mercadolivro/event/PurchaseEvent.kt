package com.ljmtecnologica.mercadolivro.event

import com.ljmtecnologica.mercadolivro.model.Purchase
import org.springframework.context.ApplicationEvent

class PurchaseEvent(
    source: Any,
    val purchase: Purchase
): ApplicationEvent(source) {
}