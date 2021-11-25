package com.assignment.payhere._global.dto

import com.assignment.payhere._global.error.BasicError
import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable

data class ErrorResponse(
    val data: Any? = null,
    val error: BasicError
)