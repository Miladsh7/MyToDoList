package com.miladsh.mytodoList.data.repository

import androidx.lifecycle.LiveData
import com.miladsh.mytodoList.data.database.ToDoDao
import com.miladsh.mytodoList.data.model.TodoEntity
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val toDoDao: ToDoDao
) {
    suspend fun insert(todoEntity: TodoEntity) = toDoDao.insert(todoEntity)

    suspend fun update(todoEntity: TodoEntity) = toDoDao.update(todoEntity)

    suspend fun delete(todoEntity: TodoEntity) = toDoDao.delete(todoEntity)

    suspend fun deleteAll() = toDoDao.deleteAll()

    fun getAllTodo() = toDoDao.getAllTodo()

    fun search(query: String): LiveData<List<TodoEntity>> {
        return toDoDao.search(query)
    }
}