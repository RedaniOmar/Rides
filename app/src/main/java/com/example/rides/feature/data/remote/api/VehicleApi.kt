package com.example.rides.feature.data.remote.api

import com.example.rides.feature.data.remote.dto.VehicleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface VehicleApi {

    @GET("vehicle/random_vehicle")
    suspend fun getVehicles(
        @Query("size") size: String
    ): VehicleResponse

    companion object {
        const val BASE_URL = "https://random-data-api.com/api/"
    }

}