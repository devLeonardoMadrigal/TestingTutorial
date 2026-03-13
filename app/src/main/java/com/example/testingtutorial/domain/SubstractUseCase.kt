package com.example.testingtutorial.domain

class SubtractUseCase constructor(private val repository : CalcRepository) {
    operator fun invoke(a: Int, b: Int) = repository.subtract(a,b)
}