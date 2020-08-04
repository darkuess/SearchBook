package com.kakaopay.assignment.presentation.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kakaopay.assignment.R
import com.kakaopay.assignment.databinding.ItemSearchBookBinding
import com.kakaopay.assignment.domain.entity.BookEntity
import com.kakaopay.assignment.presentation.util.initBinding

class SearchBookListAdapter(private val context: Context) :
    ListAdapter<BookEntity, SearchBookListAdapter.ItemViewHolder>(SearchBookListDiffCallback()) {

    inner class ItemViewHolder(
        private val binding: ItemSearchBookBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BookEntity) {
            binding.book = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(
            initBinding(
                context = context,
                layoutId = R.layout.item_search_book,
                parent = parent
            )
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class SearchBookListDiffCallback : DiffUtil.ItemCallback<BookEntity>() {
    override fun areItemsTheSame(
        oldItem: BookEntity,
        newItem: BookEntity
    ): Boolean = oldItem.isbn == newItem.isbn

    override fun areContentsTheSame(
        oldItem: BookEntity,
        newItem: BookEntity
    ): Boolean = oldItem == newItem
}