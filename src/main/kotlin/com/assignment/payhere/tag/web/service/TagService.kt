package com.assignment.payhere.tag.web.service

import com.assignment.payhere._global.error.AlreadyExistsException
import com.assignment.payhere._global.error.AuthenticationFailedException
import com.assignment.payhere._global.error.ErrorCode
import com.assignment.payhere.tag.domain.dto.TagAddRequestDTO
import com.assignment.payhere.tag.domain.dto.TagResponseDTO
import com.assignment.payhere.tag.domain.entity.Tag
import com.assignment.payhere.tag.web.repository.TagQueryRepository
import com.assignment.payhere.tag.web.repository.TagRepository
import com.assignment.payhere.user.web.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TagService(
    val tagRepository: TagRepository,
    val tagQueryRepository: TagQueryRepository,
    val userRepository: UserRepository
) {
    fun getTags(userId: Long, name: String): List<TagResponseDTO> {
        return tagQueryRepository.findByUserIdAndNameContains(userId, name).map { tag ->
            TagResponseDTO.of(tag)
        }
    }

    fun addTag(userId: Long, dto: TagAddRequestDTO): TagResponseDTO {
        val user = userRepository.findByIdOrNull(userId) ?: throw AuthenticationFailedException(ErrorCode.INVALID_LOGIN_INFO)

        if(tagRepository.existsByName(dto.name))
            throw AlreadyExistsException(ErrorCode.TAG_DUPLICATION)

        val tag = Tag(
                user = user,
                name = dto.name
        )

        return TagResponseDTO.of(tagRepository.save(tag))
    }
}