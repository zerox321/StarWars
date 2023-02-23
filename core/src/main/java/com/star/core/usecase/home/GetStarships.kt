package com.star.core.usecase.home

import com.star.core.entities.remote.StarshipsResponse
import com.star.core.repository.HomeRepo


class GetStarships(private val repo: HomeRepo) {
    suspend operator fun invoke(page: Int) : StarshipsResponse = repo.fetchStarships(page = page)
}