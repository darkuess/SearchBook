package com.kakaopay.assignment.data.api

import com.kakaopay.assignment.data.entity.BookListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface DaumSearchApi {
    @GET("/v3/search/book")
    fun getBookList(@QueryMap(encoded = true) searchOption: Map<String, String?>): Single<BookListResponse>
}