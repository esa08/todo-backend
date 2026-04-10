package com.esa.todo.dto

import jakarta.validation.constraints.NotBlank

data class CategoryRequest(
    @field:NotBlank(message = "Name tidak boleh kosong")
    val name: String
)
