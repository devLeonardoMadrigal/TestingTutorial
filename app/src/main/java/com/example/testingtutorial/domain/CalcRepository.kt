package com.example.testingtutorial.domain

interface CalcRepository {
    fun add(a : Int, b : Int) : Int
    fun subtract(a : Int, b : Int) : Int
}