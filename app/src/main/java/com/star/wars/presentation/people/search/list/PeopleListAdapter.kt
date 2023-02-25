package com.star.wars.presentation.people.search.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.star.core.entities.remote.People
import com.star.wars.databinding.PeopleItemViewBinding
import com.star.wars.presentation.people.search.list.PeopleItemDiffCallBack
import com.star.wars.presentation.people.search.list.PeopleListActions

class PeopleListAdapter(private val actions: PeopleListActions) :
    ListAdapter<People, PeopleListAdapter.PeopleListAdapterViewHolder>(PeopleItemDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleListAdapterViewHolder =
        PeopleListAdapterViewHolder(
            binding = PeopleItemViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), actions = actions
        )

    override fun onBindViewHolder(holder: PeopleListAdapterViewHolder, position: Int) =
        holder.bind(people = getItem(position))

    class PeopleListAdapterViewHolder(
        private val binding: PeopleItemViewBinding, private val actions: PeopleListActions
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(people: People?) {
            binding.run {
                root.setOnClickListener { actions.onPeopleClick(people) }
                titleTv.text = people?.name
            }
        }
    }




}