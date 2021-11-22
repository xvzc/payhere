package com.assignment.payhere._global.exception

import org.springframework.validation.BindingResult
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

class BasicError {
    private var message: String
    private var status: Int
    private var fields: List<FieldError>
    private var code: String

    private constructor(errorCode: ErrorCode, fields: List<FieldError>) {
        message = errorCode.message
        status = errorCode.status
        this.fields = fields
        this.code = errorCode.code
    }

    private constructor(errorCode: ErrorCode) {
        message = errorCode.message
        status = errorCode.status
        fields = emptyList()
        this.code = errorCode.code
    }

    class FieldError protected constructor(
        private val field: String,
        private val value: String,
        private val reason: String
    ) {
        companion object {
            fun of(field: String, value: String, reason: String): List<BasicError.FieldError> {
                val fieldErrors: MutableList<BasicError.FieldError> = ArrayList<BasicError.FieldError>()
                fieldErrors.add(BasicError.FieldError(field, value, reason))
                return fieldErrors
            }

            fun of(bindingResult: BindingResult): List<BasicError.FieldError> {
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
        fun of(errorCode: ErrorCode, bindingResult: BindingResult): BasicError {
            return BasicError(errorCode, FieldError.of(bindingResult))
        }

        fun of(errorCode: ErrorCode): BasicError {
            return BasicError(errorCode)
        }

        fun of(errorCode: ErrorCode, errors: List<FieldError>): BasicError {
            return BasicError(errorCode, errors)
        }

        fun of(e: MethodArgumentTypeMismatchException): BasicError {
            val value = if (e.value == null) "" else e.value.toString()
            val errors: List<FieldError> = FieldError.of(e.name, value, e.errorCode)
            return BasicError(ErrorCode.INVALID_TYPE_VALUE, errors)
        }
    }
}