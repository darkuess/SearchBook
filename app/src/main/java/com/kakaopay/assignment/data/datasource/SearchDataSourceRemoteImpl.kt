package com.kakaopay.assignment.data.datasource

import com.kakaopay.assignment.data.api.DaumSearchApi
import com.kakaopay.assignment.domain.entity.BookEntity
import com.kakaopay.assignment.data.entity.SearchOption
import com.kakaopay.assignment.data.entity.toEntity
import com.kakaopay.assignment.data.entity.toMap
import io.reactivex.Single

class SearchDataSourceRemoteImpl(private val api: DaumSearchApi) : SearchDataSource {

    override fun getBookList(searchOption: SearchOption?): Single<List<BookEntity>> {
        searchOption ?: return Single.error(IllegalArgumentException("searchOption is null"));
        return api.getBookList(searchOption.toMap()).map { it.toEntity() }
    }
}