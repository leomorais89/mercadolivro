package com.ljmtecnologica.mercadolivro.controller

import com.ljmtecnologica.mercadolivro.controller.request.PostBook
import com.ljmtecnologica.mercadolivro.controller.request.PutBook
import com.ljmtecnologica.mercadolivro.extension.toBook
import com.ljmtecnologica.mercadolivro.model.Book
import com.ljmtecnologica.mercadolivro.service.BookService
import com.ljmtecnologica.mercadolivro.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/books")
class BookController(
    private val bookService: BookService,
    private val customerService: CustomerService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(@PageableDefault(page = 0, size = 10) pageable: Pageable) : Page<Book>{
        return this.bookService.findAll(pageable)
    }

    @GetMapping("/active")
    @ResponseStatus(HttpStatus.OK)
    fun findByStatus(@PageableDefault(page = 0, size = 10) pageable: Pageable) : Page<Book>{
        return this.bookService.findByStatus(pageable)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id : Int) : Book{
        return this.bookService.findById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun insert(@RequestBody @Valid postBook: PostBook) : Book{
        val customer = this.customerService.findById(postBook.customerId)
        return this.bookService.insert(postBook.toBook(customer))
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestBody putBook: PutBook) : Book{
        val bookSaved = this.bookService.findById(putBook.id)
        return this.bookService.update(putBook.toBook(bookSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id : Int){
        this.bookService.delete(id)
    }

}