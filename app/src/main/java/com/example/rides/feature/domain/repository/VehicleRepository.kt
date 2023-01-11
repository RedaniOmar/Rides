package com.example.rides.feature.domain.repository

import com.example.rides.core.Resource
import com.example.rides.feature.data.remote.dto.VehicleResponse
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {

    suspend fun getVehicles(size: String): Flow<Resource<List<VehicleResponse>>>

}