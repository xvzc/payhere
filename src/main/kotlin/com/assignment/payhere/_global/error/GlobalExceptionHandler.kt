package com.assignment.payhere._global.error

import com.assignment.payhere._global.dto.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

// TODO: 21. 11. 27. Status Code
@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ErrorResponse {
        logger.info("handleMethodArgumentNotValidException", exception)
        return ErrorResponse(
            error = BasicError.of(exception)
        )
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(exception: ResourceNotFoundException): ErrorResponse {
        logger.info("handleResourceNotFoundException", exception)
        return ErrorResponse(
            error = BasicError.of(exception)
        )
    }

    @ExceptionHandler(AlreadyExistsException::class)
    fun handleAlreadyExistsException(exception: AlreadyExistsException): ErrorResponse {
        logger.info("handleAlreadyExistsException", exception)
        return ErrorResponse(
            error = BasicError.of(exception)
        )
    }

    @ExceptionHandler(AuthenticationFailedException::class)
    fun handleAuthenticationFailedException(exception: AuthenticationFailedException): ErrorResponse {
        logger.info("handleAuthenticationFailedException", exception)
        return ErrorResponse(
                error = BasicError.of(exception)
        )
    }

    /** 위에서 처리되지 않은 모든 Exception */
    @ExceptionHandler(Exception::class)
    fun handleSevereException(exception: Exception): ErrorResponse {
        logger.info("handleSevereException", exception)
        return ErrorResponse(
            error = BasicError.of(exception)
        )
    }
}