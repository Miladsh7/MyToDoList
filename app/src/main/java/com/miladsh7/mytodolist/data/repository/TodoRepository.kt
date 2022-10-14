package com.miladsh7.mytodolist.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.miladsh7.mytodolist.data.database.ToDoDao
import com.miladsh7.mytodolist.data.model.TodoEntity
import com.miladsh7.mytodolist.utils.TABLE_TODO
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