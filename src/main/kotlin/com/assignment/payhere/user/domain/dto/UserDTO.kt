package com.assignment.payhere.user.domain.dto

import com.assignment.payhere.user.domain.entity.User

data class UserResponseDTO(
    val id: Long = 0,
    val email: String = "",
) {
    companion object {
        fun of(user: User): UserResponseDTO {
            return UserResponseDTO(
                id = user.id,
                email = user.email
            )
        }
    }
}
