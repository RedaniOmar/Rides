package com.example.rides.feature.domain.usecase

interface CarbonEmissionUseCase {

    operator fun invoke(kilometer: Int): Double

    fun betweenOrEqualZeroAndFiveThousand(kilometer: Int): Boolean

    fun greaterThanFiveThousand(kilometer: Int): Boolean
}