package com.kakaopay.assignment.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BookListResponse(
    @SerializedName("meta")
    @Expose
    val meta: SearchMeta? = null,

    @SerializedName("documents")
    @Expose
    val documents: List<BookResult>? = null
)