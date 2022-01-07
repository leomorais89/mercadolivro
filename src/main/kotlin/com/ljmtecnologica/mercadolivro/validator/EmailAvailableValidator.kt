package com.ljmtecnologica.mercadolivro.validator

import com.ljmtecnologica.mercadolivro.annotations.EmailAvailable
import com.ljmtecnologica.mercadolivro.service.CustomerService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmailAvailableValidator(
    private val customerService: CustomerService
): ConstraintValidator<EmailAvailable, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if(value.isNullOrEmpty()){
           return false
        }
        return customerService.emailAvailable(value)
    }
}