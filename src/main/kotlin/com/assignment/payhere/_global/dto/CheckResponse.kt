package com.assignment.payhere._global.dto

import com.assignment.payhere._global.error.BasicError

data class CheckResponse(
    val success: Boolean,
    val error: BasicError? = null
)