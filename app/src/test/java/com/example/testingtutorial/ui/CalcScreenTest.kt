package com.example.testingtutorial.ui

import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.example.testingtutorial.data.CalcRepositoryImpl
import com.example.testingtutorial.domain.AddUseCase
import com.example.testingtutorial.domain.SubtractUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalcScreenTest {

    @JvmField
    @get:Rule
    val composeRule = createComposeRule()

    val calcVm = CalcViewModel(
        AddUseCase(CalcRepositoryImpl()),
        SubtractUseCase(CalcRepositoryImpl())
    )

    @Before
    fun setUp(){
        composeRule.setContent {
            CalcScreen(calcVm)
        }
    }

    @Test
    fun add_the_fields_button_is_visible(){
        composeRule.onNodeWithTag("add_Btn").isDisplayed()
    }

    @Test
    fun correct_result_is_showed(){
        composeRule.onNodeWithTag("FIELD_A").performTextClearance()
        composeRule.onNodeWithTag("FIELD_A").performTextInput("1")
        composeRule.onNodeWithTag("FIELD_B").performTextClearance()
        composeRule.onNodeWithTag("FIELD_B").performTextInput("1")
        composeRule.onNodeWithTag("add_Btn").performClick()
        composeRule.onNodeWithContentDescription("Text for addition result").assertTextContains("2")
    }


}