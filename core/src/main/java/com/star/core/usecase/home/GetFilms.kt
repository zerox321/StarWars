package com.star.core.usecase.home

import com.star.core.entities.remote.FilmResponse
import com.star.core.repository.HomeRepo


class GetFilms(private val repo: HomeRepo) {
    suspend operator fun invoke(page: Int): FilmResponse = repo.fetchFilms(page = page)
}