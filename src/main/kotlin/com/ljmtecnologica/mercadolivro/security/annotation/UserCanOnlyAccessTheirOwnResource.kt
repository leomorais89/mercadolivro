package com.ljmtecnologica.mercadolivro.security.annotation

import org.springframework.security.access.prepost.PreAuthorize

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("hasRole('ROLE_ADMIN') || #id == authentication.principal.id")
annotation class UserCanOnlyAccessTheirOwnResource()
