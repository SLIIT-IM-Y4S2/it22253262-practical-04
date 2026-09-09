package com.example.maddlab5

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maddlab5.databinding.ActivityTodoListBinding

class TodoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoListBinding
    private lateinit var adapter: TodoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.btnNavAdd.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish() // Optional: Finish to not pile up activities, or leave it to stack them
        }

        binding.btnNavView.setOnClickListener {
            // Already here
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.updateTodos(TodoRepository.todos.toList())
    }

    private fun setupRecyclerView() {
        adapter = TodoListAdapter(
            todos = TodoRepository.todos.toList(),
            onEditClick = { todo ->
                showEditDialog(todo)
            },
        ) { todo ->
            TodoRepository.todos.remove(todo)
            adapter.updateTodos(TodoRepository.todos.toList())
            Toast.makeText(this, "Todo Deleted", Toast.LENGTH_SHORT).show()
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun showEditDialog(todo: Todo) {
        val editText = EditText(this)
        editText.setText(todo.text)
        
        AlertDialog.Builder(this)
            .setTitle("Edit Todo")
            .setView(editText)
            .setPositiveButton("Save") { dialog, _ ->
                val newText = editText.text.toString()
                if (newText.isNotBlank()) {
                    todo.text = newText
                    adapter.updateTodos(TodoRepository.todos.toList())
                    Toast.makeText(this, "Todo Updated", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Text cannot be empty", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
