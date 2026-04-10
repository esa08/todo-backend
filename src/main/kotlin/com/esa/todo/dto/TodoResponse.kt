package com.esa.todo.dto

import java.time.LocalDateTime

data class TodoResponse(
    val id: Long,
    val categoryId: Long,
    val categoryName: String,
    val title: String,
    val notes: String?,
    val deadline: LocalDateTime?,
    val isDone: Boolean,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
)
