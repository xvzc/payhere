package com.assignment.payhere._global.util

import com.assignment.payhere._global.util.jwt.JwtProvider
import com.assignment.payhere.user.domain.entity.User
import com.auth0.jwt.JWT
import io.mockk.junit5.MockKExtension
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.ArgumentMatchers.anyString
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId

@ExtendWith(MockKExtension::class)
class JwtProviderTest {

    @Test
    fun createTest() {
        val user = User(
            id = anyLong(),
            email = anyString(),
        )
        val authType = anyString()
        val jwt = JwtProvider.createToken(user, authType)

        println("token: " + jwt.value)
        println("expireDate: " + jwt.expireDate)

        val duration = Duration.between(LocalDateTime.now(ZoneId.systemDefault()), jwt.expireDate)
        println("TTL:" + duration.seconds)

        val decoded = JWT.decode(jwt.value)
        val claims = decoded.claims

        assertEquals(user.id, claims["userId"]?.asLong())
        assertEquals(authType, claims["authType"]?.asString())
    }
}