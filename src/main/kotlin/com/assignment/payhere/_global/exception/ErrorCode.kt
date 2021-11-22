package com.assignment.payhere._global.exception

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ErrorCode(
    val status: Int,
    val code: String, val message: String
) {
    // GLOBAL
    INTERNAL_SERVER_ERROR(500, "G-000", "Internal server error"), INVALID_INPUT_VALUE(
        400,
        "G-001",
        " Invalid input value"
    ),
    METHOD_NOT_ALLOWED(405, "G-002", " Method not allowed"), INVALID_TYPE_VALUE(
        400,
        "G-003",
        " Invalid type value"
    ),
    ACCESS_DENIED(403, "G-004", "Access is Denied"),  // User
    USER_NOT_FOUND(404, "USER-000", "User not found"), EMAIL_DUPLICATION(
        400,
        "USER-001",
        "Email already exists"
    ),
    LOGIN_FAILED(400, "USER-002", "Login failed"), USER_DUPLICATION(400, "USER-003", "User already exists"),  // Board
    POST_NOT_FOUND(404, "BOARD-000", "Post not found"), BOARD_DUPLICATION(
        400,
        "BOARD-001",
        "Board already exists"
    ),
    BOARD_NOT_FOUND(400, "BOARD-002", "Board not found");

}