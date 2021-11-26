package com.assignment.payhere._global.dto

import com.assignment.payhere._global.error.BasicError

data class ListResponse<T>(
    val data: List<T>,
    val error: BasicError? = null
)