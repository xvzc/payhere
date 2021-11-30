package com.assignment.payhere._global.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
@EnableRedisRepositories
class RedisConfig(
) {
    @Value("\${spring.redis.host}") private lateinit var redisHost: String
    @Value("\${spring.redis.port}") private lateinit var redisPort: String

    @Bean
    fun redisConnectionFactory() = LettuceConnectionFactory(redisHost, redisPort.toInt())

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()

        return redisTemplate.apply {
            setConnectionFactory(redisConnectionFactory())
            keySerializer = StringRedisSerializer()
            valueSerializer = GenericJackson2JsonRedisSerializer()
        }
    }

    @Bean
    fun stringRedisTemplate() = StringRedisTemplate(redisConnectionFactory())
}
