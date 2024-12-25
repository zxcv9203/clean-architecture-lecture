package org.example.cleanarchitecturelecture.api.config

import org.example.cleanarchitecturelecture.common.exception.ErrorMessage
import org.example.cleanarchitecturelecture.common.exception.ErrorResponse
import org.example.cleanarchitecturelecture.common.util.logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = logger()

    @ExceptionHandler(HttpMessageNotReadableException::class, IllegalArgumentException::class)
    fun handleBadRequest(e: Exception): ResponseEntity<ErrorResponse> =
        ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.message ?: ErrorMessage.BAD_REQUEST.message)
            .also { log.warn("[handleBadRequest] response: $it") }
            .let { ResponseEntity.badRequest().body(it) }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> =
        ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorMessage.INTERNAL_SERVER_ERROR.message)
            .also { log.error("[handleException] response: $it", e) }
            .let { ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(it) }
}
