package com.star.core.usecase.home

import com.star.core.entities.remote.FilmResponse
import com.star.core.repository.HomeRepo


class SearchFilms(private val repo: HomeRepo) {
    suspend operator fun invoke(query: String): FilmResponse = repo.searchFilms(query = query)
}