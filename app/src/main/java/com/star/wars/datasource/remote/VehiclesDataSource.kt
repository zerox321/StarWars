package com.star.wars.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.star.core.entities.remote.Vehicle
import com.star.core.usecase.home.GetVehicles
import javax.inject.Inject

class VehiclesDataSource @Inject constructor(private val getVehicles: GetVehicles) :
    PagingSource<Int, Vehicle>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Vehicle> {
        return try {
            val page = params.key ?: 1
            val response = getVehicles.invoke(page = page)
            val rows = response.results!!
            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (rows.isEmpty()) null else page + 1
            LoadResult.Page(data = rows, prevKey = prevKey, nextKey = nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Vehicle>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}