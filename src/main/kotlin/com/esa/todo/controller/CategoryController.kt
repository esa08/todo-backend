package com.esa.todo.controller

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
    fun getAllCategories(): ResponseEntity<List<CategoryResponse>> =
        ResponseEntity.ok(categoryService.getAllCategories())

    // GET /api/categories/{id}
    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable id: Long): ResponseEntity<CategoryResponse> =
        ResponseEntity.ok(categoryService.getCategoryById(id))

    // POST /api/categories
    @PostMapping
    fun createCategory(
        @Valid @RequestBody request: CategoryRequest
    ): ResponseEntity<CategoryResponse> =
        ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(request))

    // PUT /api/categories/{id}
    @PutMapping("/{id}")
    fun updateCategory(
        @PathVariable id: Long,
        @Valid @RequestBody request: CategoryRequest
    ): ResponseEntity<CategoryResponse> =
        ResponseEntity.ok(categoryService.updateCategory(id, request))

    // DELETE /api/categories/{id}
    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Long): ResponseEntity<Map<String, String>> {
        categoryService.deleteCategory(id)
        return ResponseEntity.ok(mapOf("message" to "Kategori berhasil dihapus"))
    }
}
