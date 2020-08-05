package com.kakaopay.assignment.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.kakaopay.assignment.data.entity.SearchOption
import com.kakaopay.assignment.domain.entity.BookEntity
import com.kakaopay.assignment.domain.usecase.SearchUseCase
import com.kakaopay.assignment.presentation.base.AutoDisposeViewModel
import com.uber.autodispose.autoDispose
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class SearchBookListViewModel(private val searchUseCase: SearchUseCase) :
    AutoDisposeViewModel(), CoroutineScope {

    private val job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    val searchText = MutableLiveData<String>("")

    val triggerSearch = MutableLiveData<Boolean>(false)

    val loadSearchBook = MutableLiveData<PagingData<BookEntity>>()

    fun requestSearch() {
        triggerSearch.value = true
        val query = searchText.value ?: return
        searchUseCase.loadBookList(SearchOption(query = query, size = DEFAULT_PAGE_SIZE))
            .subscribeOn(Schedulers.io())
            .cachedIn(scope = this)
            .autoDispose(this)
            .subscribe({
                loadSearchBook.postValue(it)
            }, Timber::e)
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 50
    }
}