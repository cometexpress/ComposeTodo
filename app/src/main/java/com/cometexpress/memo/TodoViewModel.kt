package com.cometexpress.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    private var _todoItems = MutableLiveData<List<Todo>>()
    val todoList: LiveData<List<Todo>> = _todoItems

    fun getAllTodo() {
        _todoItems.value = TodoManager.getAllTodo().reversed()
    }

    fun addTodo(title: String) {
        TodoManager.addTodo(title)
        getAllTodo()
    }

    fun deleteTodo(id: Int) {
        TodoManager.deleteTodo(id)
        getAllTodo()
    }
}

