package com.example.rides.feature.domain.usecase.validation

import com.example.rides.core.UniversalText

sealed class ValidationResult {
    object Success : ValidationResult()
    data class Error(val message: UniversalText.Resource): ValidationResult()
}