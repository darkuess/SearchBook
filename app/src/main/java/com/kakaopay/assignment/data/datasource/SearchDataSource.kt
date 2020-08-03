package com.kakaopay.assignment.data.datasource

import com.kakaopay.assignment.domain.entity.BookEntity
import com.kakaopay.assignment.data.entity.SearchOption
import io.reactivex.Single

interface SearchDataSource {
    fun getBookList(searchOption: SearchOption?): Single<List<BookEntity>>
}