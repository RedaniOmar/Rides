package com.example.rides.feature.presentation.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.rides.R
import com.example.rides.databinding.LayoutBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BottomSheetFragment : BottomSheetDialogFragment() {

    private val navArgs by navArgs<BottomSheetFragmentArgs>()
    private val viewModel by viewModels<BottomSheetViewModel>()
    private var _binding: LayoutBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LayoutBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val kilometrage = navArgs.kilometrage
        viewModel.calculatingCarbonEmissions(kilometrage)

        viewModel.carbonEmissionsLiveData.observe(viewLifecycleOwner) {
            binding.tvCarbonEmissions.text = getString(R.string.carbon_emission_value, it.toString())
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}