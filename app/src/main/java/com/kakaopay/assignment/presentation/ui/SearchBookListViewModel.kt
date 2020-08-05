package com.kakaopay.assignment.presentation.ui

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.jakewharton.rxrelay2.PublishRelay
import com.kakaopay.assignment.data.entity.SearchOption
import com.kakaopay.assignment.domain.entity.BookEntity
import com.kakaopay.assignment.domain.usecase.SearchUseCase
import com.kakaopay.assignment.presentation.base.AutoDisposeViewModel
import com.kakaopay.assignment.presentation.util.SimpleTextWatcher
import com.uber.autodispose.autoDispose
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import timber.log.Timber
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

class SearchBookListViewModel(private val searchUseCase: SearchUseCase) :
    AutoDisposeViewModel(), CoroutineScope {

    private val job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    val searchText = MutableLiveData<String>("")

    val triggerSearch = MutableLiveData<Boolean>(false)

    val loadSearchBook = MutableLiveData<PagingData<BookEntity>>()

    private val autoSearchText: PublishRelay<String> = PublishRelay.create()
    val textWatcher: TextWatcher = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable) {
            autoSearchText.accept(s.toString())
        }
    }

    private val autoSearchedData: Flowable<PagingData<BookEntity>> =
        autoSearchText
            .debounce(300, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .filter { keyword -> keyword.isNotEmpty() }
            .toFlowable(BackpressureStrategy.LATEST)
            .switchMap { query ->
                searchUseCase.loadBookList(SearchOption(query = query, size = DEFAULT_PAGE_SIZE))
                    .subscribeOn(Schedulers.io())
                    .cachedIn(scope = this)
            }

    init {
        autoSearchedData.subscribeOn(Schedulers.io())
            .cachedIn(scope = this)
            .autoDispose(this)
            .subscribe(loadSearchBook::postValue, Timber::e)
    }

    fun requestSearch() {
        triggerSearch.value = true
        val query = searchText.value ?: return
        searchUseCase.loadBookList(SearchOption(query = query, size = DEFAULT_PAGE_SIZE))
            .subscribeOn(Schedulers.io())
            .cachedIn(scope = this)
            .autoDispose(this)
            .subscribe(loadSearchBook::postValue, Timber::e)
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 50
    }
}