package com.kakaopay.assignment.domain.usecase

import com.kakaopay.assignment.data.entity.SearchOption
import com.kakaopay.assignment.domain.entity.BookEntity
import io.reactivex.Single

interface SearchUseCase {
    fun getBookList(searchOption: SearchOption): Single<List<BookEntity>>
}