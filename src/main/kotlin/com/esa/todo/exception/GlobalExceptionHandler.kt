package com.esa.todo.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    // Handle 404 - data tidak ditemukan
    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFound(ex: ResourceNotFoundException): ResponseEntity<Map<String, Any>> {
        val body = mapOf(
            "status" to 404,
            "error" to "Not Found",
            "message" to (ex.message ?: "Resource tidak ditemukan")
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body)
    }

    // Handle 400 - validasi input gagal
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, Any>> {
        val errors = ex.bindingResult.fieldErrors.map { "${it.field}: ${it.defaultMessage}" }
        val body = mapOf(
            "status" to 400,
            "error" to "Validation Failed",
            "messages" to errors
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body)
    }

    // Handle error umum / server error
    @ExceptionHandler(Exception::class)
    fun handleGeneral(ex: Exception): ResponseEntity<Map<String, Any>> {
        val body = mapOf(
            "status" to 500,
            "error" to "Internal Server Error",
            "message" to (ex.message ?: "Terjadi kesalahan")
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body)
    }
}
