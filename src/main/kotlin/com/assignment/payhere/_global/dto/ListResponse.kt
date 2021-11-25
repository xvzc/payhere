package com.assignment.payhere._global.dto

import com.assignment.payhere._global.error.BasicError

data class ListResponse<T>(
    val data: T,
    val error: BasicError? = null
)