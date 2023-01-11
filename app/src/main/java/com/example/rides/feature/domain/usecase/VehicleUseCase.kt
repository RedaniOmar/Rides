package com.example.rides.feature.domain.usecase

import com.example.rides.core.Resource
import com.example.rides.feature.domain.model.VehicleDomain
import kotlinx.coroutines.flow.Flow

interface VehicleUseCase {

    suspend fun getVehicles(size: String): Flow<Resource<List<VehicleDomain>>>

}