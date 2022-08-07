package com.manish.androiddevcourse

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


fun main() {
    tasksInANormalFunction()
}

fun tasksInANormalFunction() {
    println("Running tasks")
    task1()
    CoroutineScope(Dispatchers.Default).launch {
        task2()
    }
    task3()
    task4()
}

suspend fun tasksInASuspendFunction() {
    println("Running tasks in a suspend function")
    task1()
    task2()
    task3()
    task4()
}

private fun task1() {
    println("Executing task 1")
}

private suspend fun task2() {
    println("Executing task 2 which is a long running task")
    delay(5000)
    println()
}

private fun task3() {
    println("Executing task 3")
}

private fun task4() {
    println("Executing task 4")
}