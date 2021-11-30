package com.assignment.payhere.user.web.service

import com.assignment.payhere._global.dto.CheckDTO
import com.assignment.payhere._global.error.AlreadyExistsException
import com.assignment.payhere._global.error.AuthenticationFailedException
import com.assignment.payhere._global.error.ErrorCode
import com.assignment.payhere._global.error.ResourceNotFoundException
import com.assignment.payhere._global.util.jwt.JwtProvider
import com.assignment.payhere._global.util.jwt.PayhereToken
import com.assignment.payhere.user.domain.dto.SignInRequestDTO
import com.assignment.payhere.user.domain.dto.RegisterRequestDTO
import com.assignment.payhere.user.domain.dto.UserResponseDTO
import com.assignment.payhere.user.web.repository.UserRepository
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class HomeService(
    private val userRepository: UserRepository,
    private val redisTemplate: StringRedisTemplate,
) {
    fun signIn(dto: SignInRequestDTO): PayhereToken {
        val user = userRepository.findByEmail(dto.email)
            ?: throw ResourceNotFoundException(ErrorCode.SIGN_IN_FAILED)

        if (!user.password.isMatch(dto.password))
            throw AuthenticationFailedException(ErrorCode.SIGN_IN_FAILED)


        val key = user.id.toString()
        val previousToken = redisTemplate.opsForValue().get(key)
        val token: PayhereToken

        if(previousToken!= null) {
            val expireDate = LocalDateTime.ofInstant(
                JwtProvider.decode(previousToken).expiresAt.toInstant(),
                ZoneId.systemDefault()
            )
            token = PayhereToken(
                userId = user.id,
                email = user.email,
                value = previousToken.toString(),
                expireDate = expireDate
            )
            redisTemplate.delete(key)
        } else {
            token = JwtProvider.createToken(
                user = user,
                authType = "USER"
            )
        }

        return token
    }

    fun register(dto: RegisterRequestDTO): UserResponseDTO {
        if(userRepository.existsByEmail(dto.email))
            throw AlreadyExistsException(ErrorCode.EMAIL_DUPLICATION)

        val user = dto.toEntity()

        return UserResponseDTO.of(userRepository.save(user))
    }

    fun checkEmail(email: String): CheckDTO {
        return CheckDTO(
            success = userRepository.existsByEmail(email)
        )
    }

    fun signOut(jwt: PayhereToken) {
        val duration = Duration.between(LocalDateTime.now(ZoneId.systemDefault()), jwt.expireDate)

        val ofv = redisTemplate.opsForValue()
        ofv.set(jwt.userId.toString(), jwt.value, duration)
    }
}