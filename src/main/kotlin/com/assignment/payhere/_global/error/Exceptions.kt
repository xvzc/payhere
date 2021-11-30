package com.assignment.payhere._global.error

class ResourceNotFoundException(
    errorCode: ErrorCode
) : BaseException(errorCode)

class AlreadyExistsException(
    errorCode: ErrorCode
): BaseException(errorCode)

class AuthenticationFailedException(
    errorCode: ErrorCode
): BaseException(errorCode)

class DataFormatViolatedExceiption(
    errorCode: ErrorCode
): BaseException(errorCode)
