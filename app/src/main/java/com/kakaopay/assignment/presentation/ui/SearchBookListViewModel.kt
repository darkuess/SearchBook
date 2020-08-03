package com.kakaopay.assignment.presentation.ui

import com.kakaopay.assignment.domain.usecase.SearchUseCase
import com.kakaopay.assignment.presentation.base.AutoDisposeViewModel

class SearchBookListViewModel(private val searchUseCase: SearchUseCase) : AutoDisposeViewModel() {
}