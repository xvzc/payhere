package com.assignment.payhere.receipt.domain.dto

import com.querydsl.core.annotations.QueryProjection
import java.time.ZoneId
import java.time.ZonedDateTime

class ReceiptSumProjection @QueryProjection constructor(
    val date: String,
    var income: Long,
    var outgo: Long
    ) {
}

class ReceiptSimpleProjection @QueryProjection constructor(
    var id: Long,
    val dateTime: String,
    var amount: Int,
    var tag: String
) {
}
