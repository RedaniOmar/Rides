package com.example.rides.feature.data.mapper

import com.example.rides.core.Resource
import com.example.rides.core.UniversalText
import com.example.rides.feature.data.remote.dto.VehicleResponse
import com.example.rides.feature.domain.model.VehicleDomain

fun VehicleResponse.mapToVehicleDomain(): VehicleDomain = VehicleDomain(
    carOptions, carType, color, doors, driveType, fuelType, id, kilometrage, licensePlate, makeAndModel, mileage, specs, transmission, uid, vin
)

fun List<VehicleResponse>.mapToVehiclesDomain(): List<VehicleDomain> = map(VehicleResponse::mapToVehicleDomain)


fun Resource<List<VehicleResponse>>.mapToResourceVehiclesDomain(): Resource<List<VehicleDomain>> =
    when(this){
        is Resource.Error -> Resource.Error(message ?: UniversalText.Empty)
        is Resource.Loading -> Resource.Loading()
        is Resource.Success -> Resource.Success(data?.mapToVehiclesDomain())
    }