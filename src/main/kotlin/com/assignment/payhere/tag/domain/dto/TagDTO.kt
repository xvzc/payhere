package com.assignment.payhere.tag.domain.dto

import com.assignment.payhere.tag.domain.entity.Tag
import com.assignment.payhere.user.domain.dto.UserResponseDTO
import com.assignment.payhere.user.domain.entity.User

// TODO: 2021/11/26 Bean Validation
data class TagAddRequestDTO(
    val name: String = ""
)

data class TagResponseDTO(
    val id: Long = 0,
    val name: String = ""

) {
    companion object {
        fun of(tag: Tag): TagResponseDTO {
            return TagResponseDTO(
                id = tag.id,
                name = tag.name
            )
        }
    }
}