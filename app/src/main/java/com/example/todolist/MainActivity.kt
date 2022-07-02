package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = ToDoAdapter(mutableListOf())

        rvToDoItems.adapter = todoAdapter
        rvToDoItems.layoutManager = LinearLayoutManager(this)

        btnAddToDo.setOnClickListener {
            val todoEvent = etToDoEvent.text.toString()
            if (todoEvent.isNotEmpty()) {
                val todo = TODO(todoEvent)
                todoAdapter.addToDo(todo)
                etToDoEvent.text.clear()
            }
        }

        btnDeleteDone.setOnClickListener {
            todoAdapter.deleteDone()
        }
    }
}