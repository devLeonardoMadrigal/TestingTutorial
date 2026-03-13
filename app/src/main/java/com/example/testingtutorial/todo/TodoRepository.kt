package com.example.testingtutorial.todo

interface TodoRepository {
    suspend fun getTodos() : List<Todo>
}