package com.star.wars.presentation.vehicle.search.list

import androidx.recyclerview.widget.DiffUtil
import com.star.core.entities.remote.Vehicle

class VehicleItemDiffCallBack : DiffUtil.ItemCallback<Vehicle>() {
    override fun areItemsTheSame(old: Vehicle, new: Vehicle): Boolean = old.name == new.name
    override fun areContentsTheSame(old: Vehicle, new: Vehicle): Boolean = old == new
}
