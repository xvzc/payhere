package com.assignment.payhere.receipt.domain.dto

import com.assignment.payhere._global.util.Constant
import com.querydsl.core.annotations.QueryProjection
import java.time.OffsetDateTime

class ReceiptSumProjection @QueryProjection constructor(
    date: String,
    var income: Long,
    var outgo: Long
    ) {

    var date: String

    init {
        this.date = OffsetDateTime
            .parse(date + "T00:00:00" + Constant.TIME_ZONE)
            .toLocalDate()
            .toString()
    }
}

class ReceiptSimpleProjection @QueryProjection constructor(
    var id: Long,
    date: String,
    var amount: Int,
    var tag: String
) {
    var date: String

    init {
        this.date = OffsetDateTime
            .parse(date + "T00:00:00" + Constant.TIME_ZONE)
            .toLocalDate()
            .toString()
    }
}
