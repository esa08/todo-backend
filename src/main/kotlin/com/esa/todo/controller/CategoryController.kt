package com.esa.todo.controller

import com.esa.todo.dto.ApiResponse
import com.esa.todo.dto.CategoryRequest
import com.esa.todo.dto.CategoryResponse
import com.esa.todo.service.CategoryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/categories")
class CategoryController(private val categoryService: CategoryService) {

    // GET /api/categories
    @GetMapping
    fun getAllCategories(): ResponseEntity<ApiResponse<List<CategoryResponse>>> {
        val data = categoryService.getAllCategories()
        return ResponseEntity.ok(
            ApiResponse(
                success = true,
                status = 200,
                message = "Categories retrieved successfully",
                data = data
            )
        )
    }

    // GET /api/categories/{id}
    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable id: Long): ResponseEntity<ApiResponse<CategoryResponse>> {
        val data = categoryService.getCategoryById(id)
        return ResponseEntity.ok(
            ApiResponse(
                success = true,
                status = 200,
                message = "Category retrieved successfully",
                data = data
            )
        )
    }

    // POST /api/categories
    @PostMapping
    fun createCategory(
        @Valid @RequestBody request: CategoryRequest
    ): ResponseEntity<ApiResponse<CategoryResponse>> {
        val data = categoryService.createCategory(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse(
                success = true,
                status = 201,
                message = "Category created successfully",
                data = data
            )
        )
    }

    // PUT /api/categories/{id}
    @PutMapping("/{id}")
    fun updateCategory(
        @PathVariable id: Long,
        @Valid @RequestBody request: CategoryRequest
    ): ResponseEntity<ApiResponse<CategoryResponse>> {
        val data = categoryService.updateCategory(id, request)
        return ResponseEntity.ok(
            ApiResponse(
                success = true,
                status = 200,
                message = "Category updated successfully",
                data = data
            )
        )
    }

    // DELETE /api/categories/{id}
    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Long): ResponseEntity<ApiResponse<Nothing>> {
        categoryService.deleteCategory(id)
        return ResponseEntity.ok(
            ApiResponse(
                success = true,
                status = 200,
                message = "Category deleted successfully"
            )
        )
    }
}
