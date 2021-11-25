package com.assignment.payhere.user.domain.dto

import com.assignment.payhere.user.domain.embeded.Password
import com.assignment.payhere.user.domain.entity.User

data class SignInRequestDTO(
    val email: String,
    val password: String,
)

data class RegisterRequestDTO(
    val email: String = "",
    val password: String = "",
) {
    fun toEntity(): User {
        return User(
            email = email,
            password = Password.of(password)
        )
    }
}