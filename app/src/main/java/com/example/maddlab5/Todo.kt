package com.example.maddlab5

import java.util.UUID

data class Todo(
    val id: String = UUID.randomUUID().toString(),
    var text: String,
)
