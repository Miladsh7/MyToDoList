package com.miladsh.mytodoList.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.miladsh.mytodoList.data.model.TodoEntity
import com.miladsh.mytodoList.utils.TABLE_TODO

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoEntity: TodoEntity)

    @Update
    suspend fun update(todoEntity: TodoEntity)

    @Delete
    suspend fun delete(todoEntity: TodoEntity)

    @Query("DELETE FROM $TABLE_TODO")
    suspend fun deleteAll()

    @Query("SELECT * FROM $TABLE_TODO ORDER BY id DESC")
    fun getAllTodo(): LiveData<List<TodoEntity>>

    @Query("SELECT * FROM $TABLE_TODO WHERE title LIKE '%' || :query ||'%' ORDER BY id DESC")
    fun search(query: String): LiveData<List<TodoEntity>>
}