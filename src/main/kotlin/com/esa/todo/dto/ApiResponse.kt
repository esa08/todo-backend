package com.esa.todo.dto

data class ApiResponse<T>(
    val success: Boolean,
    val status: Int,
    val message: String,
    val data: T? = null,
    val errors: List<String>? = null
)
