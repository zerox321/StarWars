package com.star.wars.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.star.core.entities.remote.Film
import com.star.core.entities.remote.People
import com.star.core.entities.remote.Vehicle
import com.star.wars.R
import com.star.wars.databinding.FragmentHomeBinding
import com.star.wars.presentation.film.search.list.FilmListActions
import com.star.wars.presentation.home.list.FilmPagedListAdapter
import com.star.wars.presentation.home.list.PeoplePagedListAdapter
import com.star.wars.presentation.home.list.VehiclePagedListAdapter
import com.star.wars.presentation.people.search.list.PeopleListActions
import com.star.wars.presentation.vehicle.search.list.VehicleListActions
import com.star.wars.utility.HtmlUtility
import com.star.wars.utility.ToastUtility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), FilmListActions, VehicleListActions, PeopleListActions {

    private val homeViewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var htmlUtility: HtmlUtility

    @Inject
    lateinit var toastUtility: ToastUtility

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val filmListAdapter by lazy {
        FilmPagedListAdapter(
            actions = this,
            htmlUtility = htmlUtility
        )
    }
    private val peopleListAdapter by lazy { PeoplePagedListAdapter(actions = this) }
    private val vehicleListAdapter by lazy {
        VehiclePagedListAdapter(
            actions = this,
            htmlUtility = htmlUtility
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bindView()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            homeViewModel.filmsList.collectLatest { result -> filmListAdapter.submitData(result) }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            homeViewModel.peopleList.collectLatest { result -> peopleListAdapter.submitData(result) }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            homeViewModel.vehicleList.collectLatest { result -> vehicleListAdapter.submitData(result) }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            filmListAdapter.loadStateFlow.collectLatest { loadStates ->
                val isLoading =
                    loadStates.refresh == LoadState.Loading && filmListAdapter.itemCount == 0
                binding.filmsLoadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            peopleListAdapter.loadStateFlow.collectLatest { loadStates ->
                val isLoading =
                    loadStates.refresh == LoadState.Loading && peopleListAdapter.itemCount == 0
                binding.peopleLoadingView.visibility = if (isLoading) View.VISIBLE else View.GONE

            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vehicleListAdapter.loadStateFlow.collectLatest { loadStates ->
                val isLoading =
                    loadStates.refresh == LoadState.Loading && vehicleListAdapter.itemCount == 0
                binding.vehicleLoadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }


    }

    private fun FragmentHomeBinding.bindView() {
        filmsRv.adapter = filmListAdapter
        peopleRv.adapter = peopleListAdapter
        vehicleRv.adapter = vehicleListAdapter
        filmsSearchIv.setOnClickListener { findNavController().navigate(R.id.openFilmSearchFragment) }
        peopleSearchIv.setOnClickListener { findNavController().navigate(R.id.openPeopleSearchFragment) }
        vehicleSearchIv.setOnClickListener { findNavController().navigate(R.id.openVehicleSearchFragment) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onFilmClick(film: Film?) {
        findNavController().navigate(R.id.openFilmDetailFragment, bundleOf("film" to film))
    }

    override fun onPeopleClick(people: People?) {
        findNavController().navigate(R.id.openPeopleDetailFragment, bundleOf("people" to people))

    }

    override fun onVehicleClick(vehicle: Vehicle?) {
        findNavController().navigate(R.id.openVehicleDetailFragment, bundleOf("vehicle" to vehicle))
    }
}


