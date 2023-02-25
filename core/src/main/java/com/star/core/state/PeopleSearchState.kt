package com.star.core.state

import com.star.core.entities.remote.PeopleResponse

sealed class PeopleSearchState {
    object Loading : PeopleSearchState()
    object Empty : PeopleSearchState()
    object Idle : PeopleSearchState()
    data class Error(val error: Throwable) : PeopleSearchState()
    data class Success(val response: PeopleResponse) : PeopleSearchState()
}