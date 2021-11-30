package com.assignment.payhere._global.util.jwt

import java.time.LocalDateTime
import java.util.*

data class PayhereToken(
    val userId: Long,
    val email: String,
    val value: String,
    val expireDate: LocalDateTime
)