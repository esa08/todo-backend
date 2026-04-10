package com.esa.todo.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class TodoRequest(
    @field:NotNull(message = "Category ID tidak boleh kosong")
    val categoryId: Long,

    @field:NotBlank(message = "Title tidak boleh kosong")
    val title: String,

    val notes: String? = null,

    val deadline: LocalDateTime? = null,

    val isDone: Boolean = false
)
