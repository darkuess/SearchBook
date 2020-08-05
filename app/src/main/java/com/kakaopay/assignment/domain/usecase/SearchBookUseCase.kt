package com.kakaopay.assignment.domain.usecase

import androidx.paging.PagingData
import com.kakaopay.assignment.data.entity.SearchOption
import com.kakaopay.assignment.domain.entity.BookEntity
import com.kakaopay.assignment.domain.repository.SearchRepository
import io.reactivex.Flowable
import io.reactivex.Single

class SearchBookUseCase(private val repository: SearchRepository) : SearchUseCase {

    override fun getBookList(searchOption: SearchOption): Single<List<BookEntity>> {
        return repository.getBookList(searchOption)
    }

    override fun loadBookList(searchOption: SearchOption): Flowable<PagingData<BookEntity>> {
        return repository.loadBookList(searchOption)
    }
}