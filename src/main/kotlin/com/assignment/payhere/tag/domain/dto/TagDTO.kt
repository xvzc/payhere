package com.assignment.payhere.tag.domain.dto

import com.assignment.payhere.tag.domain.entity.Tag
import javax.validation.constraints.NotEmpty

data class TagAddRequestDTO(
    @NotEmpty
    val name: String = ""
)

data class TagResponseDTO(
    val id: Long? = 0,
    val name: String? = ""

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