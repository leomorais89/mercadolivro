package com.ljmtecnologica.mercadolivro.annotations

import com.ljmtecnologica.mercadolivro.validator.BookStatusAvailableValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [BookStatusAvailableValidator::class])
annotation class BookStatusAvailable(
    val message: String = "Exists book not active in the list",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
