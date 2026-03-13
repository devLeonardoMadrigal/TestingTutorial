package com.example.testingtutorial.data

import com.example.testingtutorial.domain.CalcRepository

class CalcRepositoryImpl : CalcRepository {
    override fun add(a: Int, b: Int): Int {
        return a + b
    }

    override fun subtract(a: Int, b: Int): Int {
        return a - b
    }


}