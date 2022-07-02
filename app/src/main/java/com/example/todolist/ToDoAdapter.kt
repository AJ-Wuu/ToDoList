package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class ToDoAdapter (
    private val todos: MutableList<ToDo>
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addToDo(todo: ToDo) {
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }

    fun deleteDone() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvToDoEvent: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvToDoEvent.paintFlags = tvToDoEvent.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else {
            tvToDoEvent.paintFlags = tvToDoEvent.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val curToDo = todos[position]
        holder.itemView.apply {
            //Go to "build.gradle" of the module scale
            //In the plugins block, paste "id 'kotlin-android-extensions'"
            tvToDoEvent.text = curToDo.event
            cbDone.isChecked = curToDo.isChecked
            toggleStrikeThrough(tvToDoEvent, curToDo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvToDoEvent, isChecked)
                curToDo.isChecked = !curToDo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}