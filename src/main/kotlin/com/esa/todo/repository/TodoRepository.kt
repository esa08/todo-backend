package com.esa.todo.repository

import com.esa.todo.model.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : JpaRepository<Todo, Long> {

    // Query untuk mendapatkan semua todo berdasarkan category_id
    fun findByCategoryId(categoryId: Long): List<Todo>
}
