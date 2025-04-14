package com.geramotos.scheduler.handlers

import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class JsonParseErrorException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleJsonParseError(ex: HttpMessageNotReadableException): Map<String, String> {
        return mapOf(
            "error" to "JSON inválido. Verifique sintaxe e campos obrigatórios."
        )
    }
}