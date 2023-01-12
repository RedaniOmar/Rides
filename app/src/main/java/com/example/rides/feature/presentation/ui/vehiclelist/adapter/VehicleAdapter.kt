package com.example.rides.feature.presentation.ui.vehiclelist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rides.databinding.VehicleItemBinding
import com.example.rides.feature.presentation.ui.model.VehicleUiModel

class VehicleAdapter(private val listener: OnVehicleClickedListener) :
    ListAdapter<VehicleUiModel, VehicleAdapter.VehicleViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val binding =
            VehicleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehicleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class VehicleViewHolder(private val binding: VehicleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemVehicleClicked(item)
                    }
                }
            }
        }

        fun bind(currentVehicle: VehicleUiModel) {
            binding.apply {
                tvMakeAndModel.text = currentVehicle.makeAndModel
                tvVin.text = currentVehicle.vin
            }
        }

    }

    interface OnVehicleClickedListener {
        fun onItemVehicleClicked(currentVehicle: VehicleUiModel)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<VehicleUiModel>() {
            override fun areItemsTheSame(oldItem: VehicleUiModel, newItem: VehicleUiModel) =
                oldItem.uid == newItem.uid

            override fun areContentsTheSame(oldItem: VehicleUiModel, newItem: VehicleUiModel) =
                oldItem == newItem
        }
    }

}