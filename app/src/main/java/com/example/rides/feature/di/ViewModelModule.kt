package com.example.rides.feature.di

import com.example.rides.feature.data.repository.VehicleRepositoryImpl
import com.example.rides.feature.domain.repository.VehicleRepository
import com.example.rides.feature.domain.usecase.CarbonEmissionUseCase
import com.example.rides.feature.domain.usecase.CarbonEmissionUseCaseImpl
import com.example.rides.feature.domain.usecase.VehicleUseCase
import com.example.rides.feature.domain.usecase.VehicleUseCaseImpl
import com.example.rides.feature.domain.usecase.validation.ValidationInput
import com.example.rides.feature.domain.usecase.validation.ValidationInputImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun getVehicleRepository(
        vehicleRepositoryImpl: VehicleRepositoryImpl
    ): VehicleRepository

    @Binds
    abstract fun getVehicleUseCase(
        vehicleUseCaseImpl: VehicleUseCaseImpl
    ): VehicleUseCase

    @Binds
    abstract fun getValidationInput(
        validationInputImpl: ValidationInputImpl
    ): ValidationInput

    @Binds
    abstract fun getCarbonEmission(
        carbonEmissionUseCaseImpl: CarbonEmissionUseCaseImpl
    ): CarbonEmissionUseCase

}