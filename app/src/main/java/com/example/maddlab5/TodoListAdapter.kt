package com.example.maddlab5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maddlab5.databinding.ItemTodoBinding

class TodoListAdapter(
    private var todos: List<Todo>,
    private val onEditClick: (Todo) -> Unit,
    private val onDeleteClick: (Todo) -> Unit,
) : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.tvTodoText.text = todo.text
            binding.btnEdit.setOnClickListener { onEditClick(todo) }
            binding.btnDelete.setOnClickListener { onDeleteClick(todo) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    override fun getItemCount(): Int = todos.size

    fun updateTodos(newTodos: List<Todo>) {
        todos = newTodos
        notifyDataSetChanged()
    }
}
