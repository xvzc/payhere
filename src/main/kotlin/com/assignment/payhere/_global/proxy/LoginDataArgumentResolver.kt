package com.assignment.payhere._global.proxy

import com.assignment.payhere._global.annotaion.LoginData
import com.assignment.payhere._global.error.AuthenticationFailedException
import com.assignment.payhere._global.error.ErrorCode
import com.assignment.payhere._global.util.jwt.JwtProvider
import com.assignment.payhere._global.util.jwt.PayhereToken
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import java.time.LocalDateTime
import java.time.ZoneId
import javax.servlet.http.HttpServletRequest

class LoginDataArgumentResolver: HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        val hasLoginDataAnnotation = parameter.hasParameterAnnotation(LoginData::class.java)
        val hasPayhereTokenType = PayhereToken::class.java.isAssignableFrom(parameter.parameterType)

        return hasLoginDataAnnotation && hasPayhereTokenType
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): PayhereToken? {

        val request = webRequest.nativeRequest as HttpServletRequest
        val cookies = request.cookies ?: throw AuthenticationFailedException(ErrorCode.LOGIN_REQUIRED)

        val token = cookies.firstOrNull() {
            it.name == JwtProvider.COOKIE_NAME
        }?.value ?: throw AuthenticationFailedException(ErrorCode.LOGIN_REQUIRED)

        val decoded = JwtProvider.decode(token)
        val claims = decoded.claims

        return PayhereToken(
            userId = claims["userId"]?.asLong()!!,
            email = claims["email"]?.asString()!!,
            value = token,
            expireDate = LocalDateTime.ofInstant(decoded.expiresAt.toInstant(), ZoneId.systemDefault())
        )
    }
}