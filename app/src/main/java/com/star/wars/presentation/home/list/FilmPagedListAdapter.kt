package com.star.wars.presentation.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.star.core.entities.remote.Film
import com.star.wars.R
import com.star.wars.databinding.FilmsItemViewBinding
import com.star.wars.presentation.film.search.list.FilmItemDiffCallBack
import com.star.wars.presentation.film.search.list.FilmListActions
import com.star.wars.utility.HtmlUtility

class FilmPagedListAdapter(
    private val actions: FilmListActions,
    private val htmlUtility: HtmlUtility
) : PagingDataAdapter<Film, FilmPagedListAdapter.FilmAdapterViewHolder>(FilmItemDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapterViewHolder =
        FilmAdapterViewHolder(
            binding = FilmsItemViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), actions = actions, htmlUtility = htmlUtility
        )

    override fun onBindViewHolder(holder: FilmAdapterViewHolder, position: Int) =
        holder.bind(film = getItem(position))

    class FilmAdapterViewHolder(
        private val binding: FilmsItemViewBinding,
        private val actions: FilmListActions,
        private val htmlUtility: HtmlUtility
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Film?) {
            binding.run {
                root.setOnClickListener { actions.onFilmClick(film) }
                titleTv.text = film?.title
                dateTv.text = htmlUtility.fromHtml(
                    html = film?.getFormattedDate(header = root.context.getString(R.string.date))
                )

            }
        }
    }




}