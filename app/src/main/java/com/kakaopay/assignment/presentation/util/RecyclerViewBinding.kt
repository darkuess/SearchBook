package com.kakaopay.assignment.presentation.util

import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.kakaopay.assignment.domain.entity.BookEntity
import com.kakaopay.assignment.presentation.adapter.SearchBookListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@BindingAdapter("bindItem", "bindCoroutineScope")
fun RecyclerView.bindSearchBookList(items: PagingData<BookEntity>?, scope: CoroutineScope) {
    val currentAdapter = adapter as? SearchBookListAdapter ?: SearchBookListAdapter(context)
        .also {
            adapter = it
        }

    CoroutineScope(scope.coroutineContext).launch {
        items ?: return@launch
        currentAdapter.submitData(items)
    }
}