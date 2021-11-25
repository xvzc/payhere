package com.assignment.payhere._global.error

open class BaseException(
    val errorCode: ErrorCode
): RuntimeException()
