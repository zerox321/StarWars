package com.star.wars.presentation.film.search.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.star.core.entities.remote.Film
import com.star.wars.R
import com.star.wars.databinding.SearchFilmsItemViewBinding
import com.star.wars.utility.HtmlUtility

class FilmListAdapter(
    private val actions: FilmListActions, private val htmlUtility: HtmlUtility
) : ListAdapter<Film, FilmListAdapter.FilmListAdapterViewHolder>(FilmItemDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmListAdapterViewHolder =
        FilmListAdapterViewHolder(
            binding = SearchFilmsItemViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), actions = actions, htmlUtility = htmlUtility
        )

    override fun onBindViewHolder(holder: FilmListAdapterViewHolder, position: Int) =
        holder.bind(film = getItem(position))

    class FilmListAdapterViewHolder(
        private val binding: SearchFilmsItemViewBinding,
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