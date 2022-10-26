package com.miladsh.mytodoList.di

import android.content.Context
import androidx.room.Room
import com.miladsh.mytodoList.data.database.TodoDatabase
import com.miladsh.mytodoList.data.model.TodoEntity
import com.miladsh.mytodoList.utils.TODO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, TodoDatabase::class.java, TODO_DATABASE
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(todoDatabase: TodoDatabase) = todoDatabase.getTodoDao()

    @Provides
    fun provideEntity() = TodoEntity()
}