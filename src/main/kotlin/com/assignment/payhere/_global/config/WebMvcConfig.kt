package com.assignment.payhere._global.config

import com.assignment.payhere._global.proxy.SessionDataArgumentResolver
import com.assignment.payhere._global.proxy.SessionInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebMvcConfig(
        val sessionInterceptor: SessionInterceptor
): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns(
                        "/users/**",
                        "/tags/**",
                        "/receipt/**"
                )
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(SessionDataArgumentResolver())
    }
}