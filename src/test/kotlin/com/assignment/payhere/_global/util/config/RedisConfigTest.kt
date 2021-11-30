package com.assignment.payhere._global.util.config

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.StringRedisTemplate

class RedisConfigTest {
    lateinit var redisTemplate: StringRedisTemplate

    fun redisTemplateTest() {
        val valueOperations = redisTemplate.opsForValue()
        println(valueOperations.get("test"))
    }
}