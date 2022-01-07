package com.ljmtecnologica.mercadolivro.validator

import com.ljmtecnologica.mercadolivro.annotations.BookStatusAvailable
import com.ljmtecnologica.mercadolivro.enums.BookStatusEnum
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