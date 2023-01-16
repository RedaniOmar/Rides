package com.example.rides.feature.presentation.ui.vehiclelist

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.rides.R
import com.example.rides.core.Resource
import com.example.rides.databinding.FragmentVehicleListBinding
import com.example.rides.feature.presentation.ui.model.VehicleUiModel
import com.example.rides.feature.presentation.ui.util.onQueryTextSubmitted
import com.example.rides.feature.presentation.ui.vehiclelist.adapter.VehicleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehicleListFragment : Fragment(R.layout.fragment_vehicle_list),
    VehicleAdapter.OnVehicleClickedListener {

    private val viewModel by viewModels<VehicleListViewModel>()
    private var _binding: FragmentVehicleListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentVehicleListBinding.bind(view)
        val menuHost: MenuHost = requireActivity()
        menuOptions(menuHost)

        val vehicleAdapter = VehicleAdapter(this)

        binding.rvVehicle.apply {
            setHasFixedSize(true)
            adapter = vehicleAdapter
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getVehicles(vehicleAdapter.itemCount.toString())
        }

        lifecycleScope.launchWhenResumed {
            viewModel.errorValidationFlow.collect {
                binding.tvError.isVisible = true
                binding.tvError.text = it.asString(requireContext())
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }

        viewModel.vehicleLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    // Error Handled Silently
                    binding.prLoader.isVisible = false
                    binding.swipeRefreshLayout.isRefreshing = false
                }
                is Resource.Loading -> {
                    binding.tvError.isVisible = false
                    binding.prLoader.isVisible = true
                }
                is Resource.Success -> {

                    binding.swipeRefreshLayout.isRefreshing = false
                    binding.prLoader.isVisible = false

                    vehicleAdapter.submitList(it.data)
                }
            }
        }

    }

    private fun menuOptions(menuHost: MenuHost) {
        menuHost.addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.search_menu, menu)
                val searchItem = menu.findItem(R.id.action_search)
                val searchView = searchItem.actionView as SearchView
                searchView.queryHint = getString(R.string.size_to_retrieve)
                searchView.onQueryTextSubmitted {
                    viewModel.getVehicles(it.trim())
                }

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onItemVehicleClicked(currentVehicle: VehicleUiModel) {
        val action =
            VehicleListFragmentDirections.actionVehicleListFragmentToVehicleDetailsFragment(
                currentVehicle
            )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}