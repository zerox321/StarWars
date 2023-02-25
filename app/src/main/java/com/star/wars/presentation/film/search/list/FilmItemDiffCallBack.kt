package com.star.wars.presentation.film.search.list

import androidx.recyclerview.widget.DiffUtil
import com.star.core.entities.remote.Film

class FilmItemDiffCallBack : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(old: Film, new: Film): Boolean = old.title == new.title
    override fun areContentsTheSame(old: Film, new: Film): Boolean = old == new
}