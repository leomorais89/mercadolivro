package com.ljmtecnologica.mercadolivro.repository

import com.ljmtecnologica.mercadolivro.model.Purchase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PurchaseRepository: JpaRepository<Purchase, Int>, CrudRepository<Purchase, Int> {

    fun findByCustomerId(id: Int, pageable: Pageable): Page<Purchase>

}