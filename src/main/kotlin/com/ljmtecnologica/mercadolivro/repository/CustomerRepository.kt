package com.ljmtecnologica.mercadolivro.repository

import com.ljmtecnologica.mercadolivro.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerRepository : JpaRepository<Customer, Int>, CrudRepository<Customer, Int> {

    fun findByNameContaining(name : String) : List<Customer>
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Optional<Customer>

}