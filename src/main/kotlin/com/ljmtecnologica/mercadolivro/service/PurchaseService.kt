package com.ljmtecnologica.mercadolivro.service

import com.ljmtecnologica.mercadolivro.event.PurchaseEvent
import com.ljmtecnologica.mercadolivro.model.Purchase
import com.ljmtecnologica.mercadolivro.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    fun insert(purchase: Purchase): Purchase{
        val purchaseSaved = this.purchaseRepository.save(purchase)
        this.applicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseSaved))
        return purchaseSaved
    }

    fun update(purchase: Purchase){
        this.purchaseRepository.save(purchase)
    }

    fun findByCustomerId(id: Int, pageable: Pageable): Page<Purchase>{
        return this.purchaseRepository.findByCustomerId(id, pageable)
    }

}