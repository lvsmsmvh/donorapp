package com.medicalapp.donorua.utils.extensions

import java.util.regex.Pattern

fun String.findDate() =
    findStringWithRegex("[\\d]{2}/[\\d]{2}/[\\d]{2}")

fun String.findTime() =
    findStringWithRegex("[\\d]{2}:[\\d]{2}")



private fun String.findStringWithRegex(regex: String): String? {
    val myPattern = Pattern.compile(regex)
    val matcher = myPattern.matcher(this)

    var result: String? = null
    while (matcher.find()) {
        result = matcher.group(2)
    }

    return result
}