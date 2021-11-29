package com.assignment.payhere.user.web.service

import com.assignment.payhere._global.error.ResourceNotFoundException
import org.junit.Assert.*
import com.assignment.payhere.user.domain.entity.User
import com.assignment.payhere.user.web.repository.UserRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
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
    fun getUserDetailSuccessTest() {
        val user = User()

        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(user)
        }

        val dto = userService.getUser(anyLong())

        assertEquals(dto.id, user.id)
        assertEquals(dto.email, user.email)
    }

    @Test
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