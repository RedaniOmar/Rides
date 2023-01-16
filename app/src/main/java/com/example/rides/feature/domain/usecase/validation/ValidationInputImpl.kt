package com.example.rides.feature.domain.usecase.validation

import com.example.rides.R
import com.example.rides.core.UniversalText
import com.example.rides.feature.domain.util.isDigitsOnly
import javax.inject.Inject

class ValidationInputImpl @Inject constructor() : ValidationInput {

    companion object {
        private const val MINIMUM_SIZE = 1
        private const val MAXIMUM_SIZE = 100
    }

    override operator fun invoke(size: String): ValidationResult {
        return when {
            emptySize(size) -> ValidationResult.Error(UniversalText.Resource(id = R.string.error_size_empty))
            isDigitsOnly(size) -> ValidationResult.Error(UniversalText.Resource(id = R.string.error_is_digits_only))
            lessThanMinimum(size) -> ValidationResult.Error(UniversalText.Resource(id = R.string.error_less_than_minimum))
            higherThanMaximum(size) -> ValidationResult.Error(UniversalText.Resource(id = R.string.error_higher_than_maximum))
            else -> ValidationResult.Success
        }
    }

    override fun emptySize(size: String): Boolean {
        return size.trim().isEmpty()
    }

    override fun isDigitsOnly(size: String): Boolean {
        return !size.trim().isDigitsOnly()
    }

    override fun lessThanMinimum(size: String): Boolean {
        return size.trim().toInt() < MINIMUM_SIZE
    }

    override fun higherThanMaximum(size: String): Boolean {
        return size.trim().toInt() > MAXIMUM_SIZE
    }

}