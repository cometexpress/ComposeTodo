package com.cometexpress.memo

import java.time.Instant
import java.util.Date

data class Todo(
    var id: Int,
    var title: String,
    var createdAt: Date
)

fun dummyData(): List<Todo> {
    return listOf(
        Todo(id = 1, title = "Todo 1", createdAt = Date.from(Instant.now())),
        Todo(id = 2, title = "Todo 2", createdAt = Date.from(Instant.now())),
        Todo(id = 3, title = "Todo 3", createdAt = Date.from(Instant.now())),
        Todo(id = 4, title = "Todo 4", createdAt = Date.from(Instant.now()))
    )
}