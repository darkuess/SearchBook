package com.kakaopay.assignment.presentation.ui

import androidx.lifecycle.MutableLiveData
import com.kakaopay.assignment.data.entity.SearchOption
import com.kakaopay.assignment.domain.entity.BookEntity
import com.kakaopay.assignment.domain.usecase.SearchUseCase
import com.kakaopay.assignment.presentation.base.AutoDisposeViewModel
import com.uber.autodispose.autoDispose
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SearchBookListViewModel(private val searchUseCase: SearchUseCase) : AutoDisposeViewModel() {

    val loadSearchBook = MutableLiveData<List<BookEntity>>()

    val searchText = MutableLiveData<String>("")

    val triggerSearch = MutableLiveData<Boolean>(false)

    fun requestSearch() {
        triggerSearch.value = true
        val query = searchText.value ?: return
        searchUseCase.getBookList(SearchOption(query = query))
            .subscribeOn(Schedulers.io())
            .autoDispose(this)
            .subscribe(loadSearchBook::postValue, Timber::e)
    }
}