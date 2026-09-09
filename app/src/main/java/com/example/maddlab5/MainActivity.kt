package com.example.maddlab5

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.maddlab5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val text = binding.etTodo.text.toString()
            if (text.isNotBlank()) {
                val newTodo = Todo(text = text)
                TodoRepository.todos.add(newTodo)
                binding.etTodo.text.clear()
                Toast.makeText(this, "Todo Added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter a todo item", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnNavAdd.setOnClickListener {
            // Already here
        }

        binding.btnNavView.setOnClickListener {
            startActivity(Intent(this, TodoListActivity::class.java))
        }
    }
}