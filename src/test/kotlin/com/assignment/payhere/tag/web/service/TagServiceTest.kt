package com.assignment.payhere.tag.web.service

import com.assignment.payhere._global.error.AlreadyExistsException
import com.assignment.payhere._global.error.AuthenticationFailedException
import com.assignment.payhere.tag.domain.dto.TagAddRequestDTO
import com.assignment.payhere.tag.domain.entity.Tag
import com.assignment.payhere.tag.web.repository.TagQueryRepository
import com.assignment.payhere.tag.web.repository.TagRepository
import com.assignment.payhere.user.domain.entity.User
import com.assignment.payhere.user.web.repository.UserRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.Assert.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.*
import java.util.*

@ExtendWith(MockKExtension::class)
class TagServiceTest {
    @InjectMockKs lateinit var tagService: TagService
    @RelaxedMockK lateinit var tagRepository: TagRepository
    @RelaxedMockK lateinit var tagQueryRepository: TagQueryRepository
    @RelaxedMockK lateinit var userRepository: UserRepository

    @Test
    @DisplayName("getTags() 비즈니스 로직 테스트")
    fun getTagLogicTest() {
        val tag = Tag(
            id = anyLong(),
            user = User(),
            name = anyString()
        )
        val tags = arrayListOf(
            tag,
            tag,
            tag
        )

        every {
            tagQueryRepository.findByUserIdAndNameContains(anyLong(), anyString())
        } answers {
            tags
        }

        tagService.getTags(anyLong(), anyString()).forEach { each ->
            assertEquals(each.id, tag.id)
            assertEquals(each.name, tag.name)
        }
    }

    @Test
    @DisplayName("addTags() 비즈니스 로직 테스트")
    fun addTagLogicTest() {
        val addDTO = TagAddRequestDTO(
            name = anyString()
        )
        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(User())
        }

        every {
            tagRepository.existsByName(addDTO.name)
        } answers {
            false
        }

        every {
            hint(Tag::class)
            tagRepository.save(any())
        } answers {
            Tag(name = addDTO.name)
        }

        val res = tagService.addTag(anyLong(), addDTO)
        assertEquals(addDTO.name, res.name)
    }

    @Test
    @DisplayName("addTags() 유저가 존재하지 않을 때")
    fun addTagWhenUserNotFoundTest() {
        val addDTO = TagAddRequestDTO(
            name = anyString()
        )

        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(null)
        }

        assertThrows<AuthenticationFailedException> {
            tagService.addTag(anyLong(), addDTO)
        }
    }

    @Test
    @DisplayName("addTags() 태그 이름이 중복될 때")
    fun addTagWhenTagDuplicatedTest() {
        val addDTO = TagAddRequestDTO(
            name = anyString()
        )

        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(User())
        }

        every {
            hint(Boolean::class)
            tagRepository.existsByName(addDTO.name)
        } answers {
            true
        }

        assertThrows<AlreadyExistsException> {
            tagService.addTag(anyLong(), addDTO)
        }
    }
}