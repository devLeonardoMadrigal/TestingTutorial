package com.example.testingtutorial.todo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

object TodoNetwork {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create<TodoApi>()
}

interface TodoApi{
    @GET("todos")
    suspend fun getAll(): List<Todo>

}