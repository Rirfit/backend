package com.condominio.backend.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {


@ExceptionHandler(RuntimeException::class)
fun handleRuntimeException(
    ex: RuntimeException
): ResponseEntity<Map<String, String>> {

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(
            mapOf(
                "message" to (
                    ex.message
                        ?: "Erro interno"
                )
            )
        )
}


}
