package com.miladsh7.mytodolist.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miladsh7.mytodolist.data.model.TodoEntity
import com.miladsh7.mytodolist.utils.TABLE_TODO

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoEntity: TodoEntity)

    @Query("SELECT * FROM $TABLE_TODO ORDER BY id DESC")
    fun getAllTodo(): LiveData<List<TodoEntity>>

}