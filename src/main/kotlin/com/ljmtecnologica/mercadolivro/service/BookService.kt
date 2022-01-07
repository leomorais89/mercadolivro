package com.ljmtecnologica.mercadolivro.service

import com.ljmtecnologica.mercadolivro.enums.BookStatusEnum
import com.ljmtecnologica.mercadolivro.enums.ErrorEnum
import com.ljmtecnologica.mercadolivro.exception.NotFoundException
import com.ljmtecnologica.mercadolivro.model.Book
import com.ljmtecnologica.mercadolivro.model.Customer
import com.ljmtecnologica.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {

    fun findAll(pageable: Pageable) : Page<Book>{
        return this.bookRepository.findAll(pageable)
    }

    fun findByStatus(pageable: Pageable) : Page<Book>{
        return this.bookRepository.findByStatus(BookStatusEnum.ATIVO, pageable)
    }

    fun findById(id : Int) : Book{
        return this.bookRepository.findById(id).orElseThrow{NotFoundException(ErrorEnum.ML0201.message.format(id), ErrorEnum.ML0201.internalCode)}
    }

    fun insert(book: Book) : Book{
        return this.bookRepository.save(book)
    }

    fun update(book: Book) : Book{
        return this.bookRepository.save(book)
    }

    fun delete(id : Int){
        val book = this.findById(id)
        book.status = BookStatusEnum.CANCELADO
        this.bookRepository.save(book)
    }

    fun deleteByCustomer(customer: Customer){
        val books = this.bookRepository.findByCustomer(customer)
        for(book in books){
            if(book.status!! == BookStatusEnum.ATIVO){
                book.status = BookStatusEnum.DELETADO
            }
        }
        this.bookRepository.saveAll(books)
    }

    fun findAllByIds(ids: Set<Int>): Set<Book>{
        return this.bookRepository.findAllById(ids).toSet()
    }

    fun updateAll(books: List<Book>){
        this.bookRepository.saveAll(books)
    }

}