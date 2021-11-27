package com.assignment.payhere._global.proxy

import com.assignment.payhere._global.annotaion.SessionData
import com.assignment.payhere._global.error.AuthenticationFailedException
import com.assignment.payhere._global.error.ErrorCode
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpServletRequest

class SessionDataArgumentResolver(): HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        val hasSessionDataAnnotation = parameter.hasParameterAnnotation(SessionData::class.java)
        val hasLongType = Long::class.java.isAssignableFrom(parameter.parameterType)

        return hasSessionDataAnnotation && hasLongType
    }

    override fun resolveArgument(
            parameter: MethodParameter,
            mavContainer: ModelAndViewContainer?,
            webRequest: NativeWebRequest,
            binderFactory: WebDataBinderFactory?): Any {

        val request = webRequest.nativeRequest as HttpServletRequest

        return request.session.getAttribute("userId") ?: throw AuthenticationFailedException(ErrorCode.LOGIN_REQUIRED)
    }
}