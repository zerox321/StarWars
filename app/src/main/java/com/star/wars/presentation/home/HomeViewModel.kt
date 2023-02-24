package com.star.wars.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.star.wars.datasource.remote.FilmsDataSource
import com.star.wars.datasource.remote.PeopleDataSource
import com.star.wars.datasource.remote.StarshipsDataSource
import com.star.wars.datasource.remote.VehiclesDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

    private val filmsDataSource: FilmsDataSource,
    private val peopleDataSource: PeopleDataSource,
    private val vehiclesDataSource: VehiclesDataSource,
    private val starshipsDataSource: StarshipsDataSource,

    pagerConfig: PagingConfig
) : ViewModel() {
    val filmsList = Pager(pagerConfig) { filmsDataSource }.flow.cachedIn(viewModelScope)
    val peopleList = Pager(pagerConfig) { peopleDataSource }.flow.cachedIn(viewModelScope)
    val vehicleList = Pager(pagerConfig) { vehiclesDataSource }.flow.cachedIn(viewModelScope)
    val starShipList = Pager(pagerConfig) { starshipsDataSource }.flow.cachedIn(viewModelScope)
}