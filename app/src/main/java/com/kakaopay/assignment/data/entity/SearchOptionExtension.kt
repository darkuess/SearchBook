package com.kakaopay.assignment.data.entity

fun SearchOption.toMap(): Map<String, String?> {
    return mapOf(
        "query" to this@toMap.query,
        "sort" to this@toMap.sort,
        "page" to this@toMap.page?.toString(),
        "size" to this@toMap.size?.toString(),
        "target" to this@toMap.target
    )
}
