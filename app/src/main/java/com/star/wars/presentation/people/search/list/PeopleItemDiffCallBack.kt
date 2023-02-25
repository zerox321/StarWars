package com.star.wars.presentation.people.search.list

import androidx.recyclerview.widget.DiffUtil
import com.star.core.entities.remote.People

class PeopleItemDiffCallBack : DiffUtil.ItemCallback<People>() {
    override fun areItemsTheSame(old: People, new: People): Boolean = old.name == new.name
    override fun areContentsTheSame(old: People, new: People): Boolean = old == new
}