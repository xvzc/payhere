package com.assignment.payhere._global.error

import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

class BasicError {
    val message: String
    val status: Int
    val fields: List<FieldError>
    val code: String

    constructor(errorCode: ErrorCode, fields: List<FieldError>) {
        message = errorCode.message
        status = errorCode.status
        this.fields = fields
        this.code = errorCode.code
    }

    constructor(errorCode: ErrorCode) {
        message = errorCode.message
        status = errorCode.status
        fields = emptyList()
        this.code = errorCode.code
    }

    class FieldError(
        private val field: String,
        private val value: String,
        private val reason: String
    ) {
        companion object {
            fun of(field: String, value: String, reason: String): List<FieldError> {
                val fieldErrors: MutableList<FieldError> = ArrayList<FieldError>()
                fieldErrors.add(FieldError(field, value, reason))
                return fieldErrors
            }

            // BindingResult 안에는 fieldError 정보가 들어있다.
            fun of(bindingResult: BindingResult): List<FieldError> {
                val fieldErrors = bindingResult.fieldErrors
                return fieldErrors.map { error: org.springframework.validation.FieldError ->
                    FieldError(
                        error.field,
                        error.rejectedValue?.toString() ?: "",
                        error.defaultMessage ?: ""
                    )
                }
            }
        }
    }

    companion object {
        fun of(exception: MethodArgumentTypeMismatchException): BasicError {
            val value = if (exception.value == null) "" else exception.value.toString()
            val errors: List<FieldError> = FieldError.of(exception.name, value, exception.errorCode)
            return BasicError(ErrorCode.INVALID_TYPE_VALUE, errors)
        }

        fun of(exception: MethodArgumentNotValidException): BasicError =
            BasicError(ErrorCode.INVALID_INPUT_VALUE, FieldError.of(exception.bindingResult))

        fun of(exception: BaseException): BasicError =
            BasicError(exception.errorCode)

        fun of(exception: Exception): BasicError =
            BasicError(ErrorCode.INTERNAL_SERVER_ERROR)
    }
}