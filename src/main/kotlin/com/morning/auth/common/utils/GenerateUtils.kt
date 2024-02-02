package com.morning.auth.common.utils

import java.util.*

class GenerateUtils {}

fun generateUUID(): String {
    return UUID.randomUUID().toString().replace("-", "")
}

fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x, y)
}

fun sumNumbers(x: Int, y: Int): Int {
    return x + y
}


