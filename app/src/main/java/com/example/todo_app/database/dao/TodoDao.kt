package com.example.todo_app.database.dao

import androidx.room.*
import com.example.todo_app.model.Todo
import java.util.*

@Dao
interface TodoDao {
    @Insert
    fun addTodo(todo: Todo)
    @Update
    fun updateTodo(todo: Todo)
    @Delete
    fun removeTodo(todo: Todo)
    @Query("select * from todo")
    fun getAllTodo():MutableList<Todo>
    @Query("select * from todo where date=:date")
    fun getTodoByDate(date: Date):MutableList<Todo>
}
