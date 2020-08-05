package com.kakaopay.assignment.data.datasource

import androidx.paging.rxjava2.RxPagingSource
import com.kakaopay.assignment.data.api.DaumSearchApi
import com.kakaopay.assignment.data.entity.BookListResponse
import com.kakaopay.assignment.data.entity.SearchOption
import com.kakaopay.assignment.data.entity.toEntity
import com.kakaopay.assignment.data.entity.toMap
import com.kakaopay.assignment.domain.entity.BookEntity
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class PagingSearchDataSource(val api: DaumSearchApi, val searchOption: SearchOption) :
    RxPagingSource<Int, BookEntity>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, BookEntity>> {
        // Start refresh at page 1 if undefined.
        searchOption.page = params.key?.let { searchOption.page?.inc() } ?: searchOption.page
        return api.getBookList(searchOption.toMap())
            .subscribeOn(Schedulers.io())
            .map(this::toLoadResult)
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(response: BookListResponse): LoadResult<Int, BookEntity> {
        return LoadResult.Page(
            response.toEntity(),
            null,  // Only paging forward.
            if (response.meta?.isEnd == true) null else searchOption.page?.inc(),
            LoadResult.Page.COUNT_UNDEFINED,
            LoadResult.Page.COUNT_UNDEFINED
        )
    }
}