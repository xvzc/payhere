package com.assignment.payhere.user.domain.entity

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    @DisplayName("equals() 같을 때")
    fun equalsWhenEqualsTest() {
        val user1 = User(
            id = 0,
            email = ""
        )

        val user2 = User(
            id = 0,
            email = ""
        )

        assertEquals(user1, user2)
    }

    @Test
    @DisplayName("equals() id가 다를 때")
    fun equalsWhenIdNotEqualsTest() {
        assertNotEquals(
            User(id = 0),
            User(id = 1)
        )
    }

    @Test
    @DisplayName("equals() email이 다를 때")
    fun equalsWhenEmailNotEqualsTest() {
        assertNotEquals(
            User(email = "user1@gmail.com"),
            User(email = "user2@gmail.com")
        )
    }

    @Test
    @DisplayName("hashcode() 로직 테스트")
    fun hashcodeLogicTest() {
        val user = User()
        assertEquals(user.id.hashCode(), user.hashCode())
    }
}