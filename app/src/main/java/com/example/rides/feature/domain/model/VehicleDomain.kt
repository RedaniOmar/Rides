package com.example.rides.feature.domain.model

data class VehicleDomain(
    val carOptions: List<String>,
    val carType: String,
    val color: String,
    val doors: Int,
    val driveType: String,
    val fuelType: String,
    val id: Int,
    val kilometrage: Int,
    val licensePlate: String,
    val makeAndModel: String,
    val mileage: Int,
    val specs: List<String>,
    val transmission: String,
    val uid: String,
    val vin: String
)
