package com.kakaopay.assignment.domain.repository

import androidx.paging.PagingData
import com.kakaopay.assignment.data.entity.SearchOption
import com.kakaopay.assignment.domain.entity.BookEntity
import io.reactivex.Flowable
import io.reactivex.Single

interface SearchRepository {
    fun getBookList(searchOption: SearchOption): Single<List<BookEntity>>
    fun loadBookList(searchOption: SearchOption): Flowable<PagingData<BookEntity>>
}