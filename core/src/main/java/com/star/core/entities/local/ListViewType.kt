package com.star.core.entities.local

sealed class ListViewType {
    object Vertical : ListViewType()
    object Horizontal : ListViewType()
}
