package com.ljmtecnologica.mercadolivro.enums

enum class ErrorEnum(val internalCode: String, val message: String) {
    ML0001("ML-0001", "Authentication error"),
    ML0002("ML-0002", "User [%s] not exists"),
    ML0003("ML-0003", "Invalid token"),
    ML0004("ML-0004", "Access denied"),
    ML0101("ML-0101", "Customer [%s] not exists"),
    ML0201("ML-0201", "Book [%s] not exists"),
    ML0202("ML-0202", "Cannot update book with status [%s]")
}