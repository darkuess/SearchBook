package com.kakaopay.assignment.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BookResult(
    @SerializedName("title")
    @Expose
    val title: String? = null,//도서 제목

    @SerializedName("contents")
    @Expose
    val contents: String? = null,//도서 소개

    @SerializedName("url")
    @Expose
    val url: String? = null,//도서 상세 URL

    @SerializedName("isbn")
    @Expose
    val isbn: String? = null,//국제 표준 도서번호, ISBN10 또는 ISBN13, ISBN10, ISBN13 중 하나 이상 존재, 공백(' ')으로 구분

    @SerializedName("datetime")
    @Expose
    val datetime: String? = null,//도서 출판날짜, ISO 8601 형식 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]

    @SerializedName("authors")
    @Expose
    val authors: List<String>? = null,// 도서 저자 리스트

    @SerializedName("publisher")
    @Expose
    val publisher: String? = null,// 도서 출판사

    @SerializedName("translators")
    @Expose
    val translators: List<String>? = null,//  도서 번역자 리스트

    @SerializedName("price")
    @Expose
    val price: Int? = null,//도서 정가

    @SerializedName("sale_price")
    @Expose
    val salePrice: Int? = null,//  도서 판매가

    @SerializedName("thumbnail")
    @Expose
    val thumbnail: String? = null,//도서 표지 미리보기 URL

    @SerializedName("status")
    @Expose
    val status: String? = null//도서 판매 상태 정보 (정상, 품절, 절판 등) 상황에 따라 변동 가능성이 있으므로 문자열 처리 지양, 단순 노출 요소로 활용 권장
)