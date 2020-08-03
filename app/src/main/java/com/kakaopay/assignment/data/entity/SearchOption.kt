package com.kakaopay.assignment.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchOption(
    @SerializedName("query")
    @Expose
    val query: String, // 검색을 원하는 질의어

    @SerializedName("sort")
    @Expose
    val sort: String? = "accuracy", // 결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy

    @SerializedName("page")
    @Expose
    val page: Int? = 1,  // 결과 페이지 번호, 1~50 사이의 값, 기본 값 1

    @SerializedName("size")
    @Expose
    val size: Int? = 10, //한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10

    @SerializedName("target")
    @Expose
    val target: String? = "" // 검색 필드 제한사용 가능한 값: title(제목), isbn (ISBN), publisher(출판사), person(인명)
)