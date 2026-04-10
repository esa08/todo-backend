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
            .orElseThrow { ResourceNotFoundException("Todo with ID $id not found") }
        return todo.toResponse()
    }

    // GET todo by category ID
    fun getTodosByCategory(categoryId: Long): List<TodoResponse> {
        if (!categoryRepository.existsById(categoryId)) {
            throw ResourceNotFoundException("Category with ID $categoryId not found")
        }
        return todoRepository.findByCategoryId(categoryId).map { it.toResponse() }
    }

    // CREATE todo baru
    fun createTodo(request: TodoRequest): TodoResponse {
        val category = categoryRepository.findById(request.categoryId)
            .orElseThrow { ResourceNotFoundException("Category with ID ${request.categoryId} not found") }

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
            .orElseThrow { ResourceNotFoundException("Todo with ID $id not found") }

        val category = categoryRepository.findById(request.categoryId)
            .orElseThrow { ResourceNotFoundException("Category with ID ${request.categoryId} not found") }

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
            throw ResourceNotFoundException("Todo with ID $id not found")
        }
        todoRepository.deleteById(id)
    }
}
