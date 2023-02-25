package com.star.wars.presentation.film.search

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
import com.star.core.entities.remote.Film
import com.star.core.state.FilmsSearchState
import com.star.wars.R
import com.star.wars.databinding.FragmentFilmSearchBinding
import com.star.wars.presentation.film.search.list.FilmListActions
import com.star.wars.presentation.film.search.list.FilmListAdapter
import com.star.wars.utility.HtmlUtility
import com.star.wars.utility.ToastUtility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

@AndroidEntryPoint

class FilmSearchFragment : Fragment(), FilmListActions {
    private val filmSearchViewModel by viewModels<FilmSearchViewModel>()

    @Inject
    lateinit var htmlUtility: HtmlUtility

    @Inject
    lateinit var toastUtility: ToastUtility
    private val filmListAdapter by lazy {
        FilmListAdapter(actions = this, htmlUtility = htmlUtility)
    }
    private var _binding: FragmentFilmSearchBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            filmSearchViewModel.searchQuery.debounce(500).distinctUntilChanged()
                .filter { it.isNotEmpty() }.collect { query ->
                    filmSearchViewModel.searchFilm(query)
                }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            filmSearchViewModel.state.collect { state ->
                when (state) {
                    is FilmsSearchState.Error -> {
                        bindProgressView(false)
                        toastUtility.showMessage(message = getString(R.string.no_internet))
                    }
                    FilmsSearchState.Idle -> Unit
                    FilmsSearchState.Loading -> {
                        binding.emptySearch.root.visibility = View.GONE
                        bindProgressView(true)
                    }

                    is FilmsSearchState.Success -> {
                        bindProgressView(false)
                        filmListAdapter.submitList(state.response.results)
                    }
                    FilmsSearchState.Empty -> {
                        binding.emptySearch.root.visibility = View.VISIBLE
                        bindProgressView(false)
                    }
                }
            }
        }

        binding.bindView()
    }

    private fun bindProgressView(isLoading: Boolean) {
        binding.searchInput.isEnabled = !isLoading
        binding.progressView.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.filmsRv.visibility = if (isLoading) View.GONE else View.VISIBLE
    }

    private fun FragmentFilmSearchBinding.bindView() {
        filmsRv.adapter = filmListAdapter
        searchInput.doAfterTextChanged { filmSearchViewModel.searchQuery.value = it.toString() }
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onFilmClick(film: Film?) {
        findNavController().navigate(
            R.id.openFilmDetailFragmentFromSearch, bundleOf("film" to film)
        )

    }


}

