package com.example.testingtutorial.todo

import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TodoViewModelTest {

    val apiMock = mockk<TodoApi>()
    private lateinit var todoViewModel: TodoViewModel


    @Before
    fun setUp() {

        coEvery { apiMock.getAll() } returns listOf(
            Todo(1,1,"T1",false),
            Todo(2,2,"T2",false),
            Todo(3,3,"T3",false),
            Todo(4,4,"T4",true),

        )

        todoViewModel = TodoViewModel(object : TodoRepository{

            override suspend fun getTodos(): List<Todo> {
                return apiMock.getAll()
            }

        })


    }
    @Test
    fun `MY Test Todo Repository`() = runTest{

        val mockTodoRepository = mockk<TodoRepository>()
        coEvery { mockTodoRepository.getTodos() } returns listOf(Todo(1,1,"1",true))


        val result = mockTodoRepository.getTodos().toList()
        assertEquals(listOf(Todo(1,1,"1",true)),result)

    }

    @Test
    fun `MY mock viewmodel test for todo API`(){

        val mockViewModel = mockk<TodoApi>()
        coEvery { mockViewModel.getAll() } returns listOf(Todo(1,1,"1",true))

        runBlocking {
            mockViewModel.getAll()
        }

        runBlocking {
            val result = mockViewModel.getAll().toList()
            assertEquals(listOf(Todo(1,1,"1",true)),result)
        }

    }

    @Test
    fun `MY mock test for todo API based on PRADIUM's code`() {
        runTest {
            todoViewModel.getTodos()
            delay(1000)
        }


        val currentTodos = todoViewModel.todos.value

        assertThat(currentTodos).containsAnyIn(listOf(
            Todo(1, 1, "T1", false),
            Todo(2, 1, "T2", false),
            Todo(3, 2, "T3", false),
            Todo(4, 3, "T4", true)
        ))


    }


//    @Test // PRADIUM TEST
//    fun `mock test for todo API`() {
//        assertThat(todoViewModel.todos.value).isEmpty()
//
//        todoViewModel.getTodos()
//
//        runBlocking {
//            todoViewModel.todos.collect { todos ->
//
//                assertThat(todos).containsAnyIn(listOf(
//                    Todo(1, 1, "T1", false),
//                    Todo(2, 1, "T2", false),
//                    Todo(3, 2, "T3", false),
//                    Todo(4, 3, "T4", true)
//                ))
//            }
//        }
//
//
//    }

    @After
    fun tearDown() {
    }

}