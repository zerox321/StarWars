package com.star.wars.datasource.remote.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.star.core.entities.remote.People
import com.star.core.usecase.home.GetPeople
import javax.inject.Inject

class PeopleDataSource @Inject constructor(private val getPeople: GetPeople) :
    PagingSource<Int, People>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, People> {

        return try {
            val page = params.key ?: 1
            val response = getPeople.invoke(page = page)
            val rows = response.results!!
            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (rows.isEmpty()) null else page + 1

            LoadResult.Page(data = rows, prevKey = prevKey, nextKey = nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, People>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}