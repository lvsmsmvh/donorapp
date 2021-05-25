package com.medicalapp.donorua.utils.extensions

import java.util.regex.Pattern

fun String.findDate() =
    findStringWithRegex("[\\d]{2}/[\\d]{2}/[\\d]{4}")

fun String.findTime() =
    findStringWithRegex("[\\d]{2}:[\\d]{2}")

private fun String.findStringWithRegex(regex: String): String? {
    val myPattern = Pattern.compile(regex)
    val matcher = myPattern.matcher(this)

//    var wordIndex: Int = length - 1
//    while (matcher.find()) {
//        if (words.length > wordIndex) {
//            matcher.appendReplacement(sb, words.get(wordIndex))
//            wordIndex++
//        }
//    }
    var result: String? = null
    if (matcher.find()) {
        result = matcher.group()
    }

    return result
}