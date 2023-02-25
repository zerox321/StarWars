package com.star.wars.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.star.wars.datasource.remote.pagingSource.FilmsDataSource
import com.star.wars.datasource.remote.pagingSource.PeopleDataSource
import com.star.wars.datasource.remote.pagingSource.VehiclesDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    filmsDataSource: FilmsDataSource,
    peopleDataSource: PeopleDataSource,
    vehiclesDataSource: VehiclesDataSource,
    pagerConfig: PagingConfig
) : ViewModel() {
    val filmsList = Pager(pagerConfig) { filmsDataSource }.flow.cachedIn(viewModelScope)
    val peopleList = Pager(pagerConfig) { peopleDataSource }.flow.cachedIn(viewModelScope)
    val vehicleList = Pager(pagerConfig) { vehiclesDataSource }.flow.cachedIn(viewModelScope)
}