package com.example.rides.feature.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CarbonEmissionUseCaseImplTest {

    private lateinit var carbonEmissionUseCaseImpl: CarbonEmissionUseCaseImpl

    @Before
    fun setUp() {
        carbonEmissionUseCaseImpl = CarbonEmissionUseCaseImpl()
    }

    @Test
    fun `input betweenOrEqualZeroAndFiveThousand, expected is input to double`() {
        val kilometers = 0
        val result = carbonEmissionUseCaseImpl(kilometers)
        assertEquals(kilometers.toDouble(), result, DELTA)
    }

    @Test
    fun `input '5001', expected is '5001,5'`() {
        val kilometers = 5001
        val result = carbonEmissionUseCaseImpl(kilometers)
        assertEquals(5001.5, result, DELTA)
    }

    @Test
    fun `input negative, expected is '0,0'`() {
        val kilometers = -100
        val result = carbonEmissionUseCaseImpl(kilometers)
        assertEquals(0.0, result, DELTA)
    }

    companion object {
        private const val DELTA = 1e-15
    }

}