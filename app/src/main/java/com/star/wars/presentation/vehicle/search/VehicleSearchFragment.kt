package com.star.wars.presentation.vehicle.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.star.core.entities.remote.Vehicle
import com.star.core.state.VehicleSearchState
import com.star.wars.R
import com.star.wars.databinding.FragmentVehicleSearchBinding
import com.star.wars.presentation.vehicle.search.list.VehicleListActions
import com.star.wars.presentation.vehicle.search.list.VehicleListAdapter
import com.star.wars.utility.HtmlUtility
import com.star.wars.utility.ToastUtility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

@AndroidEntryPoint

class VehicleSearchFragment : Fragment(), VehicleListActions {

    private val vehicleSearchViewModel by viewModels<VehicleSearchViewModel>()

    @Inject
    lateinit var htmlUtility: HtmlUtility

    @Inject
    lateinit var toastUtility: ToastUtility
    private val vehicleListAdapter by lazy {
        VehicleListAdapter(
            actions = this,
            htmlUtility = htmlUtility
        )
    }

    private var _binding: FragmentVehicleSearchBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVehicleSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vehicleSearchViewModel.searchQuery.debounce(500).distinctUntilChanged()
                .filter { it.isNotEmpty() }.collect { query ->
                    vehicleSearchViewModel.searchVehicles(query)
                }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vehicleSearchViewModel.state.collect { state ->
                when (state) {
                    is VehicleSearchState.Error -> {
                        bindProgressView(false)
                        toastUtility.showMessage(message = getString(R.string.no_internet))
                    }
                    VehicleSearchState.Idle -> Unit
                    VehicleSearchState.Loading -> bindProgressView(true)
                    is VehicleSearchState.Success -> {
                        bindProgressView(false)
                        vehicleListAdapter.submitList(state.response.results)
                    }
                    VehicleSearchState.Empty ->{
                        binding.emptySearch.root.visibility = View.VISIBLE
                        binding.progressView.visibility = View.GONE
                    }
                }
            }
        }
        binding.bindView()
    }

    private fun bindProgressView(isLoading: Boolean) {
        binding.searchInput.isEnabled = !isLoading
        binding.progressView.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.peopleRv.visibility = if (isLoading) View.GONE else View.VISIBLE
        binding.emptySearch.root.visibility = View.GONE

    }

    private fun FragmentVehicleSearchBinding.bindView() {
        peopleRv.adapter = vehicleListAdapter
        searchInput.doAfterTextChanged { vehicleSearchViewModel.searchQuery.value = it.toString() }
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onVehicleClick(vehicle: Vehicle?) {
        findNavController().navigate(
            R.id.openVehicleDetailFragmentFromVehicleSearchFragment,
            bundleOf("vehicle" to vehicle)
        )
    }

}