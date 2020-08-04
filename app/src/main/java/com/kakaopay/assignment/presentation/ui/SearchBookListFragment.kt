package com.kakaopay.assignment.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kakaopay.assignment.R
import com.kakaopay.assignment.databinding.FragmentSearchBookListBinding
import com.kakaopay.assignment.presentation.util.inflateBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchBookListFragment : Fragment() {

    private val viewModel: SearchBookListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflateBinding<FragmentSearchBookListBinding>(
            inflater = inflater,
            layoutId = R.layout.fragment_search_book_list,
            parent = container
        ).apply {
            viewModel = this@SearchBookListFragment.viewModel
        }.run {
            root
        }
    }
}