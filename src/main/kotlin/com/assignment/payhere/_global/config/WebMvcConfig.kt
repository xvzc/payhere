package com.assignment.payhere._global.config

import com.assignment.payhere._global.proxy.LoginDataArgumentResolver
import com.assignment.payhere._global.proxy.TokenInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebMvcConfig(
        val tokenInterceptor: TokenInterceptor
): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(tokenInterceptor)
            .addPathPatterns(
                "/sign-out",
                "/redis",
                "/users/**",
                "/tags/**",
                "/receipts/**"
            )

//        registry.addInterceptor(sessionInterceptor)
//            .addPathPatterns(
//                "/users/**",
//                "/tags/**",
//                "/receipts/**"
//            )
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
//        resolvers.add(SessionDataArgumentResolver())
        resolvers.add(LoginDataArgumentResolver())
    }
}