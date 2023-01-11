package com.example.rides.feature.presentation.ui.vehiclelist

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.example.rides.R
import com.example.rides.feature.presentation.ui.util.onQueryTextSubmitted

class VehicleListFragment : Fragment(R.layout.fragment_vehicle_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuOptions(menuHost)
    }

    private fun menuOptions(menuHost: MenuHost) {
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.search_menu, menu)
                val searchItem = menu.findItem(R.id.action_search)
                val searchView = searchItem.actionView as SearchView
                searchView.onQueryTextSubmitted {
                    Toast.makeText(requireContext(), "click performed $it", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }


        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}