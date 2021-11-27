package com.assignment.payhere.user.web.service

import com.assignment.payhere._global.dto.CheckDTO
import com.assignment.payhere._global.error.AlreadyExistsException
import com.assignment.payhere._global.error.AuthenticationFailedException
import com.assignment.payhere._global.error.ErrorCode
import com.assignment.payhere._global.error.ResourceNotFoundException
import com.assignment.payhere.user.domain.dto.SignInRequestDTO
import com.assignment.payhere.user.domain.dto.RegisterRequestDTO
import com.assignment.payhere.user.domain.dto.UserResponseDTO
import com.assignment.payhere.user.web.repository.UserRepository
import org.springframework.stereotype.Service
import javax.servlet.http.HttpSession

@Service
class HomeService(
    val userRepository: UserRepository
) {
    fun signIn(dto: SignInRequestDTO, session: HttpSession): UserResponseDTO {
        val user = userRepository.findByEmail(dto.email) ?: throw ResourceNotFoundException(ErrorCode.SIGN_IN_FAILED)

        if(!user.password.isMatch(dto.password))
            throw AuthenticationFailedException(ErrorCode.SIGN_IN_FAILED)

        session.setAttribute("userId", user.id)

        return UserResponseDTO.of(user)
    }

    fun register(dto: RegisterRequestDTO): UserResponseDTO {
        if(userRepository.existsByEmail(dto.email))
            throw AlreadyExistsException(ErrorCode.EMAIL_DUPLICATION)

        val user = userRepository.save(dto.toEntity())

        return UserResponseDTO.of(user)
    }

    fun checkEmail(email: String): CheckDTO {
        return CheckDTO(
            success = userRepository.existsByEmail(email)
        )
    }
}