package com.example.testingtutorial.ui

import androidx.lifecycle.ViewModel
import com.example.testingtutorial.domain.AddUseCase
import com.example.testingtutorial.domain.SubtractUseCase

class CalcViewModel constructor(private val addUseCase: AddUseCase, private val subtractUseCase: SubtractUseCase) : ViewModel() {
    fun add(a : Int, b : Int) = addUseCase(a,b)
    fun subtract(a : Int, b : Int) = subtractUseCase(a,b)
}