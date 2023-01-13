package com.example.rides.feature.domain.usecase.validation

interface ValidationInput {

    operator fun invoke(size: String): ValidationResult

    fun isDigitsOnly(size: String): Boolean

    fun lessThanMinimum(size: String): Boolean

    fun higherThanMaximum(size: String): Boolean

}