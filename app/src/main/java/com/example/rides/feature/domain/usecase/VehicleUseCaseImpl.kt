package com.example.rides.feature.domain.usecase

import com.example.rides.core.Resource
import com.example.rides.feature.data.mapper.mapToResourceVehiclesDomain
import com.example.rides.feature.domain.model.VehicleDomain
import com.example.rides.feature.domain.repository.VehicleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VehicleUseCaseImpl @Inject constructor(
    private val vehicleRepository: VehicleRepository
): VehicleUseCase {

    override suspend fun getVehicles(size: String): Flow<Resource<List<VehicleDomain>>> =
        vehicleRepository.getVehicles(size).map {
            it.mapToResourceVehiclesDomain()
        }

}