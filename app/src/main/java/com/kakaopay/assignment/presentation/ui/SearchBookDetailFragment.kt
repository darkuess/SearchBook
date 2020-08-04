package com.kakaopay.assignment.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kakaopay.assignment.MainApplication
import com.kakaopay.assignment.R
import com.kakaopay.assignment.databinding.FragmentSearchBookDetailBinding
import com.kakaopay.assignment.domain.entity.BookEntity
import com.kakaopay.assignment.presentation.util.inflateBinding
import timber.log.Timber

class SearchBookDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflateBinding<FragmentSearchBookDetailBinding>(
            inflater = inflater,
            layoutId = R.layout.fragment_search_book_detail,
            parent = container
        ).apply {
            Timber.d("arguments = $arguments")
            book = arguments?.getSerializable(ARG_BOOK_ENTITY) as? BookEntity
        }.run {
            root
        }
    }

    companion object {
        private val context: Context = MainApplication.INSTANCE
        private val ARG_BOOK_ENTITY = context.getString(R.string.arg_book_entity)
    }
}