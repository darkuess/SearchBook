package com.kakaopay.assignment.data.entity

import com.kakaopay.assignment.domain.entity.BookEntity

fun BookListResponse.toEntity() = documents?.map { it.toEntity() } ?: emptyList()

fun BookResult.toEntity(): BookEntity {
    return BookEntity(
        title = title, // 도서 제목
        contents = contents, // 도서 소개
        url = url, // 도서 상세 URL
        isbn = isbn,//국제 표준 도서번호, ISBN10 또는 ISBN13, ISBN10, ISBN13 중 하나 이상 존재, 공백(' ')으로 구분
        datetime = datetime,//도서 출판날짜, ISO 8601 형식 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
        authors = authors,// 도서 저자 리스트
        publisher = publisher,// 도서 출판사
        translators = translators,//  도서 번역자 리스트
        price = price,//도서 정가
        salePrice = salePrice,//  도서 판매가
        thumbnail = thumbnail,//도서 표지 미리보기 URL
        status = status // 도서 판매 상태 정보 (정상, 품절, 절판 등) 상황에 따라 변동 가능성이 있으므로 문자열 처리 지양, 단순 노출 요소로 활용 권장
    )
}