package com.example.myapplication

import java.io.File

data class Customer (val name :String , val email : String , var classroom : Int) {

     fun testLet (){
         val test =  File("Test").listFiles()
         test?.let {
             println(it)
         }
     }
 }