package com.example.rides.feature.domain.mapper

import com.example.rides.core.Resource
import com.example.rides.feature.domain.model.VehicleDomain
import com.example.rides.feature.presentation.ui.model.VehicleUiModel

fun VehicleDomain.mapToVehicleUiModel(): VehicleUiModel = VehicleUiModel(
    carOptions, carType, color, doors, driveType, fuelType, id, kilometrage, licensePlate, makeAndModel, mileage, specs, transmission, uid, vin
)

fun List<VehicleDomain>.mapToVehiclesUiModel(): List<VehicleUiModel> = map(VehicleDomain::mapToVehicleUiModel)

fun Resource<List<VehicleDomain>>.mapResourceVehiclesUiModel(): Resource<List<VehicleUiModel>> =
    when(this){
        is Resource.Error -> Resource.Error(message ?: "")
        is Resource.Loading -> Resource.Loading()
        is Resource.Success -> Resource.Success(data?.mapToVehiclesUiModel()?.sortedBy { it.vin })
    }