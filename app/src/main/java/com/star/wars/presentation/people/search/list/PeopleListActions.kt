package com.star.wars.presentation.people.search.list

import com.star.core.entities.remote.People

interface PeopleListActions {
    fun onPeopleClick(people: People?)
}