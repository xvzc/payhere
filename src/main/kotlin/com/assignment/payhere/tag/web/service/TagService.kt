package com.assignment.payhere.tag.web.service

import com.assignment.payhere.tag.domain.dto.TagAddRequestDTO
import com.assignment.payhere.tag.domain.dto.TagResponseDTO
import com.assignment.payhere.tag.domain.entity.Tag
import com.assignment.payhere.tag.web.repository.TagRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TagService(
    val tagRepository: TagRepository
) {
    fun getTags(name:String, pageable: Pageable): List<TagResponseDTO> {
        return tagRepository.findByNameContains(name, pageable).map { tag ->
            TagResponseDTO.of(tag)
        }
    }

    fun addTag(dto: TagAddRequestDTO): TagResponseDTO {
        return TagResponseDTO.of(tagRepository.save(Tag(name = dto.name)))
    }
}