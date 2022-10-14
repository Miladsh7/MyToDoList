package com.miladsh7.mytodolist.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.miladsh7.mytodolist.data.model.TodoEntity
import com.miladsh7.mytodolist.utils.TABLE_TODO

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

    @Query ("SELECT * FROM $TABLE_TODO WHERE title LIKE '%' || :query ||'%'")
    fun search(query: String): LiveData<List<TodoEntity>>
}