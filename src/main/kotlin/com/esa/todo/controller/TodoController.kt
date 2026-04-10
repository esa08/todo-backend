package com.esa.todo.controller

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
    fun getAllTodos(): ResponseEntity<List<TodoResponse>> =
        ResponseEntity.ok(todoService.getAllTodos())

    // GET /api/todos/{id}
    @GetMapping("/{id}")
    fun getTodoById(@PathVariable id: Long): ResponseEntity<TodoResponse> =
        ResponseEntity.ok(todoService.getTodoById(id))

    // GET /api/todos/category/{categoryId}
    @GetMapping("/category/{categoryId}")
    fun getTodosByCategory(@PathVariable categoryId: Long): ResponseEntity<List<TodoResponse>> =
        ResponseEntity.ok(todoService.getTodosByCategory(categoryId))

    // POST /api/todos
    @PostMapping
    fun createTodo(
        @Valid @RequestBody request: TodoRequest
    ): ResponseEntity<TodoResponse> =
        ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(request))

    // PUT /api/todos/{id}
    @PutMapping("/{id}")
    fun updateTodo(
        @PathVariable id: Long,
        @Valid @RequestBody request: TodoRequest
    ): ResponseEntity<TodoResponse> =
        ResponseEntity.ok(todoService.updateTodo(id, request))

    // DELETE /api/todos/{id}
    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable id: Long): ResponseEntity<Map<String, String>> {
        todoService.deleteTodo(id)
        return ResponseEntity.ok(mapOf("message" to "Todo berhasil dihapus"))
    }
}
