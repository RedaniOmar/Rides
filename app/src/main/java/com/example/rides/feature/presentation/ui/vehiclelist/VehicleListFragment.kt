package com.example.rides.feature.presentation.ui.vehiclelist

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.example.rides.R
import com.example.rides.core.Resource
import com.example.rides.feature.presentation.ui.util.onQueryTextSubmitted
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehicleListFragment : Fragment(R.layout.fragment_vehicle_list) {

    private val viewModel by viewModels<VehicleListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuOptions(menuHost)

        viewModel.vehicleLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    // Error Handled Silently
                }
                is Resource.Loading -> {
                    // Loading
                }
                is Resource.Success -> {
                    // Success
                    Toast.makeText(requireContext(), "${it.data}", Toast.LENGTH_SHORT).show()
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
                searchView.onQueryTextSubmitted {
                    viewModel.getVehicles(it)
                }

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }


        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}