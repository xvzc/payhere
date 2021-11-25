package com.assignment.payhere.tag.domain.dto

data class TagAddRequestDTO(
    val name: String = ""
)
data class TagResponseDTO(
    val id: Long = 0,
    val name: String = ""
)