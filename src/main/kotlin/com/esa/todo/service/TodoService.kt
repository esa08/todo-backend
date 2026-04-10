package com.esa.todo.service

import com.esa.todo.dto.TodoRequest
import com.esa.todo.dto.TodoResponse
import com.esa.todo.exception.ResourceNotFoundException
import com.esa.todo.model.Todo
import com.esa.todo.repository.CategoryRepository
import com.esa.todo.repository.TodoRepository
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoRepository: TodoRepository,
    private val categoryRepository: CategoryRepository
) {

    // Konversi Entity → Response DTO
    private fun Todo.toResponse() = TodoResponse(
        id = id,
        categoryId = category.id,
        categoryName = category.name,
        title = title,
        notes = notes,
        deadline = deadline,
        isDone = isDone,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    // GET semua todo
    fun getAllTodos(): List<TodoResponse> =
        todoRepository.findAll().map { it.toResponse() }

    // GET todo by ID
    fun getTodoById(id: Long): TodoResponse {
        val todo = todoRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Todo dengan ID $id tidak ditemukan") }
        return todo.toResponse()
    }

    // GET todo by category ID
    fun getTodosByCategory(categoryId: Long): List<TodoResponse> {
        if (!categoryRepository.existsById(categoryId)) {
            throw ResourceNotFoundException("Kategori dengan ID $categoryId tidak ditemukan")
        }
        return todoRepository.findByCategoryId(categoryId).map { it.toResponse() }
    }

    // CREATE todo baru
    fun createTodo(request: TodoRequest): TodoResponse {
        val category = categoryRepository.findById(request.categoryId)
            .orElseThrow { ResourceNotFoundException("Kategori dengan ID ${request.categoryId} tidak ditemukan") }

        val todo = Todo(
            category = category,
            title = request.title,
            notes = request.notes,
            deadline = request.deadline,
            isDone = request.isDone
        )
        return todoRepository.save(todo).toResponse()
    }

    // UPDATE todo
    fun updateTodo(id: Long, request: TodoRequest): TodoResponse {
        val existing = todoRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Todo dengan ID $id tidak ditemukan") }

        val category = categoryRepository.findById(request.categoryId)
            .orElseThrow { ResourceNotFoundException("Kategori dengan ID ${request.categoryId} tidak ditemukan") }

        val updated = existing.copy(
            category = category,
            title = request.title,
            notes = request.notes,
            deadline = request.deadline,
            isDone = request.isDone
        )
        return todoRepository.save(updated).toResponse()
    }

    // DELETE todo
    fun deleteTodo(id: Long) {
        if (!todoRepository.existsById(id)) {
            throw ResourceNotFoundException("Todo dengan ID $id tidak ditemukan")
        }
        todoRepository.deleteById(id)
    }
}
