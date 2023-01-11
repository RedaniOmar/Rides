package com.example.rides.feature.presentation.ui.vehiclelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rides.core.Resource
import com.example.rides.feature.domain.mapper.mapResourceVehiclesUiModel
import com.example.rides.feature.domain.usecase.VehicleUseCase
import com.example.rides.feature.presentation.ui.model.VehicleUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleListViewModel @Inject constructor(
    private val vehicleUseCase: VehicleUseCase
) : ViewModel() {

    private val _vehicleLiveData = MutableLiveData<Resource<List<VehicleUiModel>>>()
    val vehicleLiveData: LiveData<Resource<List<VehicleUiModel>>> = _vehicleLiveData

    fun getVehicles(size: String) {
        viewModelScope.launch {
            vehicleUseCase.getVehicles(size).onEach {
                _vehicleLiveData.value = it.mapResourceVehiclesUiModel()
            }.launchIn(this)
        }
    }

}