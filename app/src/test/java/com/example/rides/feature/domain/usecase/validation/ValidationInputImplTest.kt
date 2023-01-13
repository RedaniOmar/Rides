package com.example.rides.feature.domain.usecase.validation

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ValidationInputImplTest {

    private lateinit var validationInputImpl: ValidationInputImpl

    @Before
    fun setUp() {
        validationInputImpl = ValidationInputImpl()
    }

    @Test
    fun `blank, returns error`() {
        val input = "    "
        val result = validationInputImpl(input)
        assertTrue(result is ValidationResult.Error)
    }

    @Test
    fun `contains only letters, returns error`() {
        val input = "raheem"
        val result = validationInputImpl(input)
        assertTrue(result is ValidationResult.Error)
    }

    @Test
    fun `contains letters, contains digits, returns error`() {
        val input = "rah45eem"
        val result = validationInputImpl(input)
        assertTrue(result is ValidationResult.Error)
    }

    @Test
    fun `in range, contains only digits, returns success`() {
        val input = "66"
        val result = validationInputImpl(input)
        assertTrue(result is ValidationResult.Success)
    }

    @Test
    fun `contains digits between spaces, returns success`() {
        val input = "  66  "
        val result = validationInputImpl(input)
        assertTrue(result is ValidationResult.Success)
    }

    @Test
    fun `contains spaces between digits, returns error`() {
        val input = "6  6"
        val result = validationInputImpl(input)
        assertTrue(result is ValidationResult.Error)
    }

    @Test
    fun `more than maximum, contains only digit, returns error`() {
        val input = "120"
        val result = validationInputImpl(input)
        assertTrue(result is ValidationResult.Error)
    }

    @Test
    fun `less than minimum, contains only digit, returns error`() {
        val input = "0"
        val result = validationInputImpl(input)
        assertTrue(result is ValidationResult.Error)
    }

    @Test
    fun `less than minimum, contains negative digits, returns error`() {
        val input = "-34"
        val result = validationInputImpl(input)
        assertTrue(result is ValidationResult.Error)
    }

    @Test
    fun `in range, contains dots, returns error`() {
        val input = "34.6"
        val result = validationInputImpl(input)
        assertTrue(result is ValidationResult.Error)
    }

    @Test
    fun `in range, contains commas, returns error`() {
        val input = "34,6"
        val result = validationInputImpl(input)
        assertTrue(result is ValidationResult.Error)
    }

}