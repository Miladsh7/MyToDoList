package com.miladsh7.mytodolist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.miladsh7.mytodolist.data.model.TodoEntity

@Database(entities = [TodoEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun getTodoDao(): ToDoDao
}