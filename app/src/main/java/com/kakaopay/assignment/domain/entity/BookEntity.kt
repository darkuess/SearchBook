package com.kakaopay.assignment.domain.entity

import java.io.Serializable

data class BookEntity(
    val title: String? = null,//도서 제목
    val contents: String? = null,//도서 소개
    val url: String? = null,//도서 상세 URL
    val isbn: String? = null,//국제 표준 도서번호, ISBN10 또는 ISBN13, ISBN10, ISBN13 중 하나 이상 존재, 공백(' ')으로 구분
    val datetime: String? = null,//도서 출판날짜, ISO 8601 형식 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
    val authors: List<String>? = null,// 도서 저자 리스트
    val publisher: String? = null,// 도서 출판사
    val translators: List<String>? = null,//  도서 번역자 리스트
    val price: Int? = null,//도서 정가
    val salePrice: Int? = null,//  도서 판매가
    val thumbnail: String? = null,//도서 표지 미리보기 URL
    val status: String? = null//도서 판매 상태 정보 (정상, 품절, 절판 등) 상황에 따라 변동 가능성이 있으므로 문자열 처리 지양, 단순 노출 요소로 활용 권장
) : Serializable