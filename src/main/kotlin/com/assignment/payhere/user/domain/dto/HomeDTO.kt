package com.assignment.payhere.user.domain.dto

import com.assignment.payhere.user.domain.embeded.Password
import com.assignment.payhere.user.domain.entity.User
import javax.validation.constraints.Email
import javax.validation.constraints.Size

data class SignInRequestDTO(
    val email: String,
    val password: String,
)

data class RegisterRequestDTO(
        @field:Email(message = "Invalid email format")
        val email: String = "",

        @field:Size(min = 4, max = 10)
        val password: String = "",
) {
    fun toEntity(): User {
        return User(
            email = email,
            password = Password.of(password)
        )
    }
}