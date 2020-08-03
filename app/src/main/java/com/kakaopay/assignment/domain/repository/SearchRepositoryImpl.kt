package com.kakaopay.assignment.domain.repository

import com.kakaopay.assignment.data.datasource.SearchDataSource
import com.kakaopay.assignment.data.entity.SearchOption

class SearchRepositoryImpl(val remoteDataSource: SearchDataSource) : SearchRepository {

    override fun getBookList(searchOption: SearchOption) =
        remoteDataSource.getBookList(searchOption)
}