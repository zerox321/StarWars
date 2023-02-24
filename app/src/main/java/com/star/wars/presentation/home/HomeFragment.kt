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
import com.star.wars.presentation.home.list.FilmListAdapter
import com.star.wars.presentation.home.list.PeopleListAdapter
import com.star.wars.presentation.home.list.VehicleListAdapter
import com.star.wars.utility.HtmlUtility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), FilmListAdapter.FilmListActions,
    VehicleListAdapter.VehicleListActions, PeopleListAdapter.PeopleListActions {

    @Inject
    lateinit var htmlUtility: HtmlUtility
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel>()
    private val filmListAdapter by lazy {
        FilmListAdapter(actions = this, htmlUtility = htmlUtility).apply {
            addLoadStateListener { state ->
                val isLoading = state.refresh == LoadState.Loading && itemCount == 0

            }
        }
    }
    private val peopleListAdapter by lazy {
        PeopleListAdapter(actions = this).apply {
            addLoadStateListener { state ->
                val isLoading = state.refresh == LoadState.Loading && itemCount == 0

            }
        }
    }
    private val vehicleListAdapter by lazy {
        VehicleListAdapter(actions = this, htmlUtility = htmlUtility).apply {
            addLoadStateListener { state ->
                val isLoading = state.refresh == LoadState.Loading && itemCount == 0


            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            filmsRv.adapter = filmListAdapter
            peopleRv.adapter = peopleListAdapter
            vehicleRv.adapter = vehicleListAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            homeViewModel.filmsList.collectLatest { result -> filmListAdapter.submitData(result) }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            homeViewModel.peopleList.collectLatest { result -> peopleListAdapter.submitData(result) }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            homeViewModel.vehicleList.collectLatest { result -> vehicleListAdapter.submitData(result) }
        }

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