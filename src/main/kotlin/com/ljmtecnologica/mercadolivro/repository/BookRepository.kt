package com.ljmtecnologica.mercadolivro.repository

import com.ljmtecnologica.mercadolivro.model.enums.BookStatusEnum
import com.ljmtecnologica.mercadolivro.model.Book
import com.ljmtecnologica.mercadolivro.model.Customer
import com.ljmtecnologica.mercadolivro.repository.custom.BookCustomRepository
import com.ljmtecnologica.mercadolivro.repository.custom.impl.BookCustomRepositoryImpl
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Int>, CrudRepository<Book, Int>, BookCustomRepository {

    fun findByStatus(status : BookStatusEnum, pageable: Pageable) : Page<Book>
    fun findByCustomer(customer: Customer) : List<Book>

}