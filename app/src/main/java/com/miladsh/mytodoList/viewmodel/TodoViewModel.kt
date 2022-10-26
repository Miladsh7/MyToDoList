package com.miladsh.mytodoList.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.miladsh.mytodoList.data.model.TodoEntity
import com.miladsh.mytodoList.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {

    val todoLiveData = liveData(Dispatchers.IO) {
        emitSource(todoRepository.getAllTodo())
    }

    fun insert(todoEntity: TodoEntity) = viewModelScope.launch {
        todoRepository.insert(todoEntity)
    }

    fun update(todoEntity: TodoEntity) = viewModelScope.launch {
        todoRepository.update(todoEntity)
    }

    fun delete(todoEntity: TodoEntity) = viewModelScope.launch {
        todoRepository.delete(todoEntity)
    }

    fun deleteAll() = viewModelScope.launch {
        todoRepository.deleteAll()
    }

    fun search(query: String): LiveData<List<TodoEntity>> {
        return todoRepository.search(query)
    }
}