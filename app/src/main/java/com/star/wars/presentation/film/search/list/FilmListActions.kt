package com.star.wars.presentation.film.search.list

import com.star.core.entities.remote.Film

interface FilmListActions {
    fun onFilmClick(film: Film?)
}