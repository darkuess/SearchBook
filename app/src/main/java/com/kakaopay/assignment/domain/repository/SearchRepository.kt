package com.kakaopay.assignment.domain.repository

import com.kakaopay.assignment.domain.entity.BookEntity
import com.kakaopay.assignment.data.entity.SearchOption
import io.reactivex.Single

interface SearchRepository {
    fun getBookList(searchOption: SearchOption): Single<List<BookEntity>>
}