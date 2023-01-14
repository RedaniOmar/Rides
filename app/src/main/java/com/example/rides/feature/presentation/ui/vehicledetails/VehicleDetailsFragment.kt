package com.example.rides.feature.presentation.ui.vehicledetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rides.R
import com.example.rides.databinding.FragmentVehicleDetailsBinding

class VehicleDetailsFragment : Fragment(R.layout.fragment_vehicle_details) {

    private val navArgs by navArgs<VehicleDetailsFragmentArgs>()
    private var _binding: FragmentVehicleDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentVehicleDetailsBinding.bind(view)

        val vehicleUiModel = navArgs.vehicleUiModel

        binding.apply {
            tvVin.text = getString(R.string.vin_value, vehicleUiModel.vin)
            tvMakeAndModel.text = getString(R.string.make_and_model_value, vehicleUiModel.makeAndModel)
            tvColor.text = getString(R.string.color_value, vehicleUiModel.color)
            tvCarType.text = getString(R.string.car_type_value, vehicleUiModel.carType)
        }

        binding.bottomSheetBtn.setOnClickListener {
            val action = VehicleDetailsFragmentDirections.actionVehicleDetailsFragmentToBottomSheetFragment(vehicleUiModel.kilometrage)
            findNavController().navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}