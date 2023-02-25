package com.star.core.usecase.home

import com.star.core.entities.remote.PeopleResponse
import com.star.core.repository.HomeRepo


class SearchPeople(private val repo: HomeRepo) {
    suspend operator fun invoke(query: String): PeopleResponse = repo.searchPeople(query = query)
}