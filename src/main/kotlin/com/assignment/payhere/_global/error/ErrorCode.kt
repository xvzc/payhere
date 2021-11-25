package com.assignment.payhere._global.error

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ErrorCode(
    var status: Int,
    var code: String,
    var message: String
) {
    // GLOBAL
    INTERNAL_SERVER_ERROR(500, "G-000", "Internal server error"),
    INVALID_INPUT_VALUE( 400, "G-001", " Invalid input value" ),
    METHOD_NOT_ALLOWED(405, "G-002", " Method not allowed"),
    INVALID_TYPE_VALUE( 400, "G-003", " Invalid type value" ),
    ACCESS_DENIED(403, "G-004", "Access is Denied"),

    // User
    USER_NOT_FOUND(404, "USER-000", "User not found"),
    EMAIL_DUPLICATION( 400, "USER-001", "Email already exists" ),
    SIGN_IN_FAILED(400, "USER-002", "Incorrect username or password"),
}