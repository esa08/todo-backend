package com.esa.todo.controller

import com.esa.todo.dto.ApiResponse
import com.esa.todo.dto.TodoRequest
import com.esa.todo.dto.TodoResponse
import com.esa.todo.service.TodoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todos")
class TodoController(private val todoService: TodoService) {

    // GET /api/todos
    @GetMapping
    fun getAllTodos(): ResponseEntity<ApiResponse<List<TodoResponse>>> {
        val data = todoService.getAllTodos()
        return ResponseEntity.ok(
            ApiResponse(
                success = true,
                status = 200,
                message = "Todos retrieved successfully",
                data = data
            )
        )
    }

    // GET /api/todos/{id}
    @GetMapping("/{id}")
    fun getTodoById(@PathVariable id: Long): ResponseEntity<ApiResponse<TodoResponse>> {
        val data = todoService.getTodoById(id)
        return ResponseEntity.ok(
            ApiResponse(
                success = true,
                status = 200,
                message = "Todo retrieved successfully",
                data = data
            )
        )
    }

    // GET /api/todos/category/{categoryId}
    @GetMapping("/category/{categoryId}")
    fun getTodosByCategory(@PathVariable categoryId: Long): ResponseEntity<ApiResponse<List<TodoResponse>>> {
        val data = todoService.getTodosByCategory(categoryId)
        return ResponseEntity.ok(
            ApiResponse(
                success = true,
                status = 200,
                message = "Todos by category retrieved successfully",
                data = data
            )
        )
    }

    // POST /api/todos
    @PostMapping
    fun createTodo(
        @Valid @RequestBody request: TodoRequest
    ): ResponseEntity<ApiResponse<TodoResponse>> {
        val data = todoService.createTodo(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse(
                success = true,
                status = 201,
                message = "Todo created successfully",
                data = data
            )
        )
    }

    // PUT /api/todos/{id}
    @PutMapping("/{id}")
    fun updateTodo(
        @PathVariable id: Long,
        @Valid @RequestBody request: TodoRequest
    ): ResponseEntity<ApiResponse<TodoResponse>> {
        val data = todoService.updateTodo(id, request)
        return ResponseEntity.ok(
            ApiResponse(
                success = true,
                status = 200,
                message = "Todo updated successfully",
                data = data
            )
        )
    }

    // DELETE /api/todos/{id}
    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable id: Long): ResponseEntity<ApiResponse<Nothing>> {
        todoService.deleteTodo(id)
        return ResponseEntity.ok(
            ApiResponse(
                success = true,
                status = 200,
                message = "Todo deleted successfully"
            )
        )
    }
}
