package com.example.testingtutorial.domain

import com.example.testingtutorial.data.CalcRepositoryImpl
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AddUseCaseTest {

    /*
    TRie A's of testing
    ----AAA----

    Arrange ->    Set up everything required for the test
    Act     ->    Invoke the function under test
    Assert  ->    Assert the expectations

     */

    private lateinit var addUseCase: AddUseCase
    private lateinit var subtractUseCase: SubtractUseCase
    private val calcRepository = CalcRepositoryImpl()

    @Before //Launched before every test
    fun setUp() {
        addUseCase = AddUseCase(calcRepository)
    }

    @Test
    fun `Test when a = 0 and b = -100`(){

        assertEquals(addUseCase(a=0,b=-100), -100)
    }

    @Test
    fun `Test with a = Integer Max and b = 1`(){
        assertEquals(Int.MAX_VALUE + 1,addUseCase(a = Int.MAX_VALUE, b=1))
    }


    @Test
    fun `Test when a = 50 and b = -50`(){
        assertThat(addUseCase(50,-50)).isEqualTo(0)
    }

    @After
    fun tearDown() {

    }

}