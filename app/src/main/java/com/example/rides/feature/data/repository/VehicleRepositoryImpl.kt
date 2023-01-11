package com.example.rides.feature.data.repository

import com.example.rides.core.Resource
import com.example.rides.feature.data.remote.api.VehicleApi
import com.example.rides.feature.data.remote.dto.VehicleResponse
import com.example.rides.feature.domain.repository.VehicleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class VehicleRepositoryImpl @Inject constructor(
    private val vehicleApi: VehicleApi
): VehicleRepository {

    override suspend fun getVehicles(size: String): Flow<Resource<List<VehicleResponse>>> =
    flow {
        try {
            emit(Resource.Loading())
            val data = vehicleApi.getVehicles(size)
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "http exception"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "io exception",
                )
            )
        }
    }

}