package com.assignment.payhere.user.web.service

import com.assignment.payhere._global.error.ResourceNotFoundException
import org.junit.Assert.*
import com.assignment.payhere.user.domain.entity.User
import com.assignment.payhere.user.web.repository.UserRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.*
import java.util.*

@ExtendWith(MockKExtension::class)
class UserServiceTest {
    @InjectMockKs private lateinit var userService: UserService
    @RelaxedMockK private lateinit var userRepository: UserRepository

    @Test
    @DisplayName("getUserDetail() 비즈니스 로직 테스트")
    fun getUserDetailLogicTest() {
        val user = User()

        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(user)
        }

        val res = userService.getUser(anyLong())

        assertEquals(user.id, res.id)
        assertEquals(user.email, res.email)
    }

    @Test
    @DisplayName("getUserDetail()  해당하는 유저가 존재하지 않을 때")
    fun getUserDetailUserNotFoundTest() {
        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(null)
        }

        assertThrows<ResourceNotFoundException> {
            userService.getUser(anyLong())
        }
    }
}