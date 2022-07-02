package com.example.todolist

data class ToDo (
    val event: String,
    var isChecked: Boolean = false
)