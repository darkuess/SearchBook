package com.kakaopay.assignment.presentation.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kakaopay.assignment.domain.entity.BookEntity
import com.kakaopay.assignment.presentation.adapter.SearchBookListAdapter

@BindingAdapter("bindItem")
fun RecyclerView.bindSearchBookList(items: List<BookEntity>?) {
    val currentAdapter = adapter as? SearchBookListAdapter ?: SearchBookListAdapter(context)
        .also {
            adapter = it
        }

    currentAdapter.submitList(items)
}