package com.ljmtecnologica.mercadolivro.exception

import com.ljmtecnologica.mercadolivro.enums.ErrorEnum
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun handlerNotFoundException(ex : NotFoundException) : ErrorResponse{
        return ErrorResponse(
            httpCode = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = ex.message,
            internalCode = ex.internalCode
        )
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException::class)
    fun handlerBadRequestException(ex: BadRequestException): ErrorResponse{
        return ErrorResponse(
            httpCode = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = ex.message,
            internalCode = ex.internalCode
        )
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ErrorResponse{
        return ErrorResponse(
            httpCode = HttpStatus.UNPROCESSABLE_ENTITY.value(),
            error = HttpStatus.UNPROCESSABLE_ENTITY.name,
            message = ex.bindingResult.fieldErrors.map { it.defaultMessage }.toString()
        )
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException::class)
    fun handlerAccessDeniedException(ex: AccessDeniedException): ErrorResponse{
        return ErrorResponse(
            httpCode = HttpStatus.FORBIDDEN.value(),
            error = HttpStatus.FORBIDDEN.name,
            message = ErrorEnum.ML0004.message,
            internalCode = ErrorEnum.ML0004.internalCode
        )
    }

}