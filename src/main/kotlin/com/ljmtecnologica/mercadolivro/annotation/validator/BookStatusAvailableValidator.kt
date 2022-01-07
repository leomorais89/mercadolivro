package com.ljmtecnologica.mercadolivro.annotation.validator

import com.ljmtecnologica.mercadolivro.annotation.BookStatusAvailable
import com.ljmtecnologica.mercadolivro.model.enums.BookStatusEnum
import com.ljmtecnologica.mercadolivro.service.BookService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class BookStatusAvailableValidator(
    private val bookService: BookService
): ConstraintValidator<BookStatusAvailable, Set<Int>> {

    override fun isValid(value: Set<Int>?, context: ConstraintValidatorContext?): Boolean {
        val books = this.bookService.findAllByIds(value!!)
        val booksFiltered = books.filter { it.status != BookStatusEnum.ATIVO }
        if(booksFiltered.isNotEmpty()){
            return false
        }
        return true
    }
}