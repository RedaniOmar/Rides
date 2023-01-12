package com.example.rides.feature.presentation.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VehicleUiModel(
    val carType: String,
    val color: String,
    val makeAndModel: String,
    val uid: String,
    val vin: String
): Parcelable