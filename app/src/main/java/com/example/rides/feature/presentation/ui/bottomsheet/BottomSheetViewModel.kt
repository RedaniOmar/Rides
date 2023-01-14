package com.example.rides.feature.presentation.ui.bottomsheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.rides.feature.domain.usecase.CarbonEmissionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomSheetViewModel @Inject constructor(
    private val carbonEmissionUseCase: CarbonEmissionUseCase
): ViewModel() {

    private val _carbonEmissionsLiveData = MutableLiveData<Double>()
    val carbonEmissionsLiveData: LiveData<Double> = _carbonEmissionsLiveData

    fun calculatingCarbonEmissions(kilometrage: Int) {
        _carbonEmissionsLiveData.value = carbonEmissionUseCase(kilometrage)
    }

}