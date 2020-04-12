package com.example.myapplication

import java.util.*

fun sum (a :Int , b : Int) = a +b
 fun sum2 (a : Int , b : Int) : Int {
     return a+b
 }

fun listDisplay () {
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }


}
fun describe(obj: Any): String? {
    when (obj) {
        1 -> "One"
        is String -> "String"
        !is String -> "Not String"
        else -> "Unknown"
    }
    return null
}