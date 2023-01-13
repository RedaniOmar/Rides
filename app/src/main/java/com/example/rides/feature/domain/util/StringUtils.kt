package com.example.rides.feature.domain.util

fun String.isDigitsOnly() = all(Char::isDigit) && isNotEmpty()