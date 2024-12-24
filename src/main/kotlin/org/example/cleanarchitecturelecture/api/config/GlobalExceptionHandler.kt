package org.example.cleanarchitecturelecture.api.config

import org.example.cleanarchitecturelecture.common.exception.ErrorMessage
import org.example.cleanarchitecturelecture.common.exception.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ErrorResponse =
        ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.message ?: ErrorMessage.BAD_REQUEST.message)

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ErrorResponse =
        ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorMessage.INTERNAL_SERVER_ERROR.message)
}
