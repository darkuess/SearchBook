package com.kakaopay.assignment.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.kakaopay.assignment.data.api.DaumSearchApi
import com.kakaopay.assignment.data.datasource.PagingSearchDataSource
import com.kakaopay.assignment.data.datasource.SearchDataSource
import com.kakaopay.assignment.data.entity.SearchOption
import com.kakaopay.assignment.domain.entity.BookEntity
import io.reactivex.Flowable
import io.reactivex.Single

class PagedSearchRepository(
    private val api: DaumSearchApi,
    private val remoteDataSource: SearchDataSource? = null
) : SearchRepository {

    override fun getBookList(searchOption: SearchOption): Single<List<BookEntity>> {
        remoteDataSource
            ?: return Single.error(UnsupportedOperationException("SearchDataSource is null"))
        return remoteDataSource.getBookList(searchOption)
    }

    override fun loadBookList(searchOption: SearchOption): Flowable<PagingData<BookEntity>> {
        return Pager(PagingConfig(searchOption.size ?: DEFAULT_PAGE_SIZE)) {
            PagingSearchDataSource(
                api = api,
                searchOption = searchOption
            )
        }.flowable
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 50
    }
}
