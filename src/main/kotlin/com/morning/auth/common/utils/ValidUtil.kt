package com.morning.auth.common.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

fun validPasswordPattern(password: String): Matcher {
    val pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[\\W_]).{10,32}$")
    val matcher = pattern.matcher(password)
    return matcher
}
