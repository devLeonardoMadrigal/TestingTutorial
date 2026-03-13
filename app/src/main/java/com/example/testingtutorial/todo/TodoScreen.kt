package com.example.testingtutorial.todo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TodoScreen(modifier: Modifier = Modifier, todos: List<Todo>) {
    LazyColumn(Modifier.fillMaxSize()) {

        items(todos){
            Text(it.title)
        }
    }
}