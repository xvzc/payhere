package com.assignment.payhere._global.error

import com.assignment.payhere._global.dto.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ErrorResponse {
        logger.info("handleMethodArgumentNotValidException", exception)
        return ErrorResponse(
            error = BasicError.of(exception)
        )
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleResourceNotFoundException(exception: ResourceNotFoundException): ErrorResponse {
        logger.info("handleResourceNotFoundException", exception)
        return ErrorResponse(
            error = BasicError.of(exception)
        )
    }

    @ExceptionHandler(AlreadyExistsException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleAlreadyExistsException(exception: AlreadyExistsException): ErrorResponse {
        logger.info("handleAlreadyExistsException", exception)
        return ErrorResponse(
            error = BasicError.of(exception)
        )
    }

    @ExceptionHandler(AuthenticationFailedException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleAuthenticationFailedException(exception: AuthenticationFailedException): ErrorResponse {
        logger.info("handleAuthenticationFailedException", exception)
        return ErrorResponse(
                error = BasicError.of(exception)
        )
    }

    /** 위에서 처리되지 않은 모든 Exception */
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleSevereException(exception: Exception): ErrorResponse {
        logger.info("handleSevereException", exception)
        return ErrorResponse(
            error = BasicError.of(exception)
        )
    }
}