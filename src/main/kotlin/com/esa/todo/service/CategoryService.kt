package com.esa.todo.service

import com.esa.todo.dto.CategoryRequest
import com.esa.todo.dto.CategoryResponse
import com.esa.todo.exception.ResourceNotFoundException
import com.esa.todo.model.Category
import com.esa.todo.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

    // Konversi Entity → Response DTO
    private fun Category.toResponse() = CategoryResponse(
        id = id,
        name = name,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    // GET semua kategori
    fun getAllCategories(): List<CategoryResponse> =
        categoryRepository.findAll().map { it.toResponse() }

    // GET kategori by ID
    fun getCategoryById(id: Long): CategoryResponse {
        val category = categoryRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Kategori dengan ID $id tidak ditemukan") }
        return category.toResponse()
    }

    // CREATE kategori baru
    fun createCategory(request: CategoryRequest): CategoryResponse {
        val category = Category(name = request.name)
        return categoryRepository.save(category).toResponse()
    }

    // UPDATE kategori
    fun updateCategory(id: Long, request: CategoryRequest): CategoryResponse {
        val existing = categoryRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Kategori dengan ID $id tidak ditemukan") }
        val updated = existing.copy(name = request.name)
        return categoryRepository.save(updated).toResponse()
    }

    // DELETE kategori
    fun deleteCategory(id: Long) {
        if (!categoryRepository.existsById(id)) {
            throw ResourceNotFoundException("Kategori dengan ID $id tidak ditemukan")
        }
        categoryRepository.deleteById(id)
    }
}
