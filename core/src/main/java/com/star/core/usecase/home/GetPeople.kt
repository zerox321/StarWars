package com.star.core.usecase.home

import com.star.core.entities.remote.PeopleResponse
import com.star.core.repository.HomeRepo


class GetPeople(private val repo: HomeRepo) {
    suspend operator fun invoke(page: Int) : PeopleResponse = repo.fetchPeople(page = page)
}