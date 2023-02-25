package com.star.wars.presentation.people.search

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
import com.star.core.entities.remote.People
import com.star.core.state.PeopleSearchState
import com.star.wars.R
import com.star.wars.databinding.FragmentPeopleSearchBinding
import com.star.wars.presentation.people.search.list.PeopleListActions
import com.star.wars.presentation.people.search.list.PeopleListAdapter
import com.star.wars.utility.HtmlUtility
import com.star.wars.utility.ToastUtility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

@AndroidEntryPoint

class PeopleSearchFragment : Fragment(), PeopleListActions {

    private val peopleSearchViewModel by viewModels<PeopleSearchViewModel>()

    @Inject
    lateinit var htmlUtility: HtmlUtility

    @Inject
    lateinit var toastUtility: ToastUtility
    private val peopleListAdapter by lazy { PeopleListAdapter(actions = this) }


    private var _binding: FragmentPeopleSearchBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            peopleSearchViewModel.searchQuery.debounce(500).distinctUntilChanged()
                .filter { it.isNotEmpty() }.collect { query ->
                    peopleSearchViewModel.searchPeople(query)
                }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            peopleSearchViewModel.state.collect { state ->
                when (state) {
                    is PeopleSearchState.Error -> {
                        bindProgressView(false)
                        toastUtility.showMessage(message = getString(R.string.no_internet))
                    }
                    PeopleSearchState.Idle -> Unit
                    PeopleSearchState.Loading -> {
                        bindProgressView(true)
                        binding.emptySearch.root.visibility = View.GONE

                    }
                    is PeopleSearchState.Success -> {
                        bindProgressView(false)
                        peopleListAdapter.submitList(state.response.results)
                    }
                    PeopleSearchState.Empty -> {
                        bindProgressView(false)
                        binding.emptySearch.root.visibility = View.VISIBLE
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

    private fun FragmentPeopleSearchBinding.bindView() {
        peopleRv.adapter = peopleListAdapter
        searchInput.doAfterTextChanged { peopleSearchViewModel.searchQuery.value = it.toString() }
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPeopleClick(people: People?) {
        findNavController().navigate(
            R.id.openPeopleDetailFragmentFromPeopleSearchFragment, bundleOf("people" to people)
        )
    }


}