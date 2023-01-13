package com.example.rides.feature.presentation.ui.vehiclelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rides.core.Resource
import com.example.rides.core.UniversalText
import com.example.rides.feature.domain.mapper.mapResourceVehiclesUiModel
import com.example.rides.feature.domain.usecase.VehicleUseCase
import com.example.rides.feature.domain.usecase.validation.ValidationInput
import com.example.rides.feature.domain.usecase.validation.ValidationResult
import com.example.rides.feature.presentation.ui.model.VehicleUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleListViewModel @Inject constructor(
    private val vehicleUseCase: VehicleUseCase,
    private val validationDigits: ValidationInput
) : ViewModel() {

    private val _vehicleLiveData = MutableLiveData<Resource<List<VehicleUiModel>>>()
    val vehicleLiveData: LiveData<Resource<List<VehicleUiModel>>> = _vehicleLiveData

    private val _errorValidationChannel = Channel<UniversalText>()
    val errorValidationFlow = _errorValidationChannel.receiveAsFlow()

    private fun inputIsValid(size: String): Boolean {
        val result = validationDigits(size)
        if (result is ValidationResult.Error) {
            viewModelScope.launch {
                _errorValidationChannel.send(result.message)
            }
            return false
        }
        return true
    }

    fun getVehicles(size: String) {
        if (!inputIsValid(size)) return
        viewModelScope.launch {
            vehicleUseCase.getVehicles(size).onEach {
                _vehicleLiveData.value = it.mapResourceVehiclesUiModel()
            }.launchIn(this)
        }
    }

}