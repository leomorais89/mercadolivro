package com.ljmtecnologica.mercadolivro.annotations

import com.ljmtecnologica.mercadolivro.validator.EmailAvailableValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [EmailAvailableValidator::class])
annotation class EmailAvailable(
    val message: String = "Email already exists",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
