package com.assignment.payhere.user.web.service

import com.assignment.payhere._global.error.ErrorCode
import com.assignment.payhere._global.error.ResourceNotFoundException
import com.assignment.payhere.user.domain.dto.UserResponseDTO
import com.assignment.payhere.user.web.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun getUser(userId: Long): UserResponseDTO {
        val user = userRepository.findById(userId).orElseThrow {
            ResourceNotFoundException(ErrorCode.USER_NOT_FOUND)
        }

        return UserResponseDTO.of(user)
    }
}