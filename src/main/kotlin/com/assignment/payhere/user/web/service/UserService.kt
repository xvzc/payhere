package com.assignment.payhere.user.web.service

import com.assignment.payhere.user.web.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository
) {
//    fun addUser(dto: UserAddRequestDTO): Boolean {
//        when(userRepository.existsByEmail(dto.email)) {
//            true -> throw AlreadyExistsException(ErrorCode.EMAIL_DUPLICATION)
//        }
//
//        userRepository.save(dto.toEntity())
//
//        return true;
//    }
}