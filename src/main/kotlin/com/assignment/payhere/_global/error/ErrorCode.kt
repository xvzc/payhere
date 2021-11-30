package com.assignment.payhere._global.error

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ErrorCode(
    var code: String,
    var message: String
) {
    // GLOBAL
    INTERNAL_SERVER_ERROR("G-000", "Internal server error"),
    INVALID_INPUT_VALUE( "G-001", " Invalid input value" ),
    METHOD_NOT_ALLOWED( "G-002", " Method not allowed"),
    INVALID_TYPE_VALUE( "G-003", " Invalid type value" ),
    ACCESS_DENIED("G-004", "Access is Denied"),
    LOGIN_REQUIRED("G-005", "Login required"),
    INVALID_LOGIN_TOKEN("G-006", "Invalid login token"),
    TOKEN_EXPIRED("G-007", "Token is expired"),
    INVALID_DATE("GLOBAL-008", "Invalid Date Format"),

    // User
    USER_NOT_FOUND("USER-000", "User not found"),
    EMAIL_DUPLICATION( "USER-001", "Email already exists" ),
    SIGN_IN_FAILED("USER-002", "Incorrect username or password"),
    INVALID_LOGIN_INFO("USER-003", "Wrong login information"),

    // Tag
    TAG_DUPLICATION("TAG-000", "Tag already exists"),
    TAG_NOT_FOUND("TAG-001", "Tag not found"),

    // Receipt
    RECEIPT_NOT_FOUND("RECEIPT-000", "Receipt not found"),


}