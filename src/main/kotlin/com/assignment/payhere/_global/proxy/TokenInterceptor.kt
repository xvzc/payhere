package com.assignment.payhere._global.proxy

import com.assignment.payhere._global.error.AuthenticationFailedException
import com.assignment.payhere._global.error.ErrorCode
import com.assignment.payhere._global.util.jwt.JwtProvider
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TokenInterceptor(
    private val redisTemplate: StringRedisTemplate
): HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val cookies = request.cookies ?: throw AuthenticationFailedException(ErrorCode.LOGIN_REQUIRED)

        val token = cookies.firstOrNull {
            it.name == JwtProvider.COOKIE_NAME
        }?.value ?: throw AuthenticationFailedException(ErrorCode.LOGIN_REQUIRED)

        val jwt = JwtProvider.verify(token)
        val claims = jwt.claims
        val userId = claims["userId"]?.asLong()
            ?: throw AuthenticationFailedException(ErrorCode.INVALID_LOGIN_TOKEN)

        val valueOperations = redisTemplate.opsForValue()

        Optional.ofNullable(valueOperations.get(userId.toString())).ifPresent {
            throw AuthenticationFailedException(ErrorCode.LOGIN_REQUIRED)
        }

        return true
    }
}