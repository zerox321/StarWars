package com.star.wars.presentation.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.star.core.entities.remote.Vehicle
import com.star.wars.R
import com.star.wars.databinding.VehicleItemViewBinding
import com.star.wars.presentation.vehicle.search.list.VehicleItemDiffCallBack
import com.star.wars.presentation.vehicle.search.list.VehicleListActions
import com.star.wars.utility.HtmlUtility

class VehiclePagedListAdapter(
    private val actions: VehicleListActions,
    private val htmlUtility: HtmlUtility
) :
    PagingDataAdapter<Vehicle, VehiclePagedListAdapter.VehicleListAdapterViewHolder>(
        VehicleItemDiffCallBack()
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VehicleListAdapterViewHolder = VehicleListAdapterViewHolder(
        binding = VehicleItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ), actions = actions, htmlUtility = htmlUtility
    )

    override fun onBindViewHolder(holder: VehicleListAdapterViewHolder, position: Int) =
        holder.bind(vehicle = getItem(position))

    class VehicleListAdapterViewHolder(
        private val binding: VehicleItemViewBinding,
        private val htmlUtility: HtmlUtility,
        private val actions: VehicleListActions
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(vehicle: Vehicle?) {
            binding.run {
                root.setOnClickListener { actions.onVehicleClick(vehicle) }
                titleTv.text = vehicle?.name
                modelTv.text = htmlUtility.fromHtml(
                    html = vehicle?.getFormattedModel(header = root.context.getString(R.string.model))
                )
            }
        }
    }


}