package com.esa.todo.dto

import java.time.LocalDateTime

data class CategoryResponse(
    val id: Long,
    val name: String,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
)
