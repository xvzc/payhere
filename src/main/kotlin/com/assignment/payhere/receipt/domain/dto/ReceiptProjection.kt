package com.assignment.payhere.receipt.domain.dto

import com.querydsl.core.annotations.QueryProjection

data class ReceiptSumProjection @QueryProjection constructor(
    val date: String = "".toString(),
    val income: Long = 0,
    val outgo: Long = 0,
)

data class ReceiptSimpleProjection @QueryProjection constructor(
    val id: Long = 0,
    val date: String = "".toString(),
    val amount: Int = 0,
    val tag: String = ""
)
