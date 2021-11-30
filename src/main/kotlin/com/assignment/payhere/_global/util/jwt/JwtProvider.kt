package com.assignment.payhere._global.util.jwt

import com.assignment.payhere._global.error.AuthenticationFailedException
import com.assignment.payhere._global.error.ErrorCode
import com.assignment.payhere.user.domain.entity.User
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import com.auth0.jwt.interfaces.DecodedJWT
import java.lang.Exception
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class JwtProvider {
    companion object {
        val ISSURE = "SPRINGBOOT"
        val SCRET = "S3CR3T"
        val COOKIE_NAME = "token"

        fun createToken(user: User, authType: String): PayhereToken {
            val now = LocalDateTime.now()
            val expiredAt = now.plusSeconds(86400)
            val token = JWT.create()
                .withClaim("userId", user.id)
                .withClaim("email", user.email)
                .withClaim("authType", authType)
                .withIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .withIssuer(ISSURE)
                .withExpiresAt(Date.from(expiredAt.atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC256(SCRET))

            return PayhereToken(
                userId = user.id,
                email = user.email,
                expireDate =  expiredAt,
                value = token
            )
        }

        fun decode(token: String): DecodedJWT {
            return try {
                JWT.decode(token)
            } catch (exception: Exception){
                exception.printStackTrace()
                throw AuthenticationFailedException(ErrorCode.INVALID_LOGIN_TOKEN)
            }
        }

        fun verify(token: String): DecodedJWT {
            val jwt = JWT.require(Algorithm.HMAC256(SCRET))
                .withIssuer(ISSURE)
                .build()

            return try {
                jwt.verify(token)
            } catch (exception: Exception){
                exception.printStackTrace()
                when(exception) {
                    is TokenExpiredException -> throw AuthenticationFailedException(ErrorCode.TOKEN_EXPIRED)
                    else -> throw AuthenticationFailedException(ErrorCode.INVALID_LOGIN_TOKEN)
                }
            }
        }
    }
}