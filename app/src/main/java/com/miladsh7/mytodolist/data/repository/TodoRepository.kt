package com.miladsh7.mytodolist.data.repository

import com.miladsh7.mytodolist.data.database.ToDoDao
import com.miladsh7.mytodolist.data.model.TodoEntity
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val toDoDao: ToDoDao
) {
    suspend fun insert(todoEntity: TodoEntity) = toDoDao.insert(todoEntity)

    fun getAllTodo() = toDoDao.getAllTodo()
}