package com.example.rides.feature.domain.usecase

import javax.inject.Inject

class CarbonEmissionUseCaseImpl @Inject constructor() : CarbonEmissionUseCase {

    companion object {
        private const val ZERO = 0
        private const val FIVE_THOUSAND = 5000
        private const val ONE_POINT_FIVE = 1.5
    }

    override operator fun invoke(kilometer: Int): Double {
        return when {
            betweenOrEqualZeroAndFiveThousand(kilometer) -> kilometer.toDouble()
            greaterThanFiveThousand(kilometer) -> FIVE_THOUSAND + ((kilometer - FIVE_THOUSAND) * ONE_POINT_FIVE)
            else -> ZERO.toDouble()
        }
    }

    override fun betweenOrEqualZeroAndFiveThousand(kilometer: Int): Boolean {
        return kilometer in ZERO..FIVE_THOUSAND
    }

    override fun greaterThanFiveThousand(kilometer: Int): Boolean {
        return kilometer > FIVE_THOUSAND
    }


}