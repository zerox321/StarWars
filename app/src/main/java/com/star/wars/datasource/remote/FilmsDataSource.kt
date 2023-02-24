package com.star.wars.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.star.core.entities.remote.Film
import com.star.core.usecase.home.GetFilms
import javax.inject.Inject

class FilmsDataSource @Inject constructor(private val getFilms: GetFilms) :
    PagingSource<Int, Film>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {

        return try {
            val page = params.key ?: 1
            val response = getFilms.invoke(page = page)
            val rows = response.results!!
            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (rows.isEmpty()) null else page + 1

            LoadResult.Page(data = rows, prevKey = prevKey, nextKey = nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}