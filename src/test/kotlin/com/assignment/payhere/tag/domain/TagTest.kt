package com.assignment.payhere.tag.domain

import com.assignment.payhere.tag.domain.entity.Tag
import com.assignment.payhere.user.domain.entity.User
import org.junit.Assert.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class TagTest {
    @Test
    fun isOwnedByLogicTest() {
        val owner = User(id = 1)
        val other = User(id = 2)
        val tag = Tag(user = owner)

        assertTrue(tag.isOwnedBy(owner))
        assertFalse(tag.isOwnedBy(other))
    }

    @Test
    @DisplayName("equals() 같을 때")
    fun equalsWhenEqualsTest() {
        val tag1 = Tag(id = 0, name = "test")
        val tag2 = Tag(id = 0, name = "test")
        assertEquals(tag1, tag2)
    }

    @Test
    @DisplayName("equals() id가 다를 때")
    fun equalsWhenIdNotEqualsTest() {
        assertNotEquals(
            Tag(id = 0),
            Tag(id = 1)
        )
    }

    @Test
    @DisplayName("equals() name이 다를 때")
    fun equalsWhenNameNotEqualsTest() {
        assertNotEquals(
            Tag(name = "test1"),
            Tag(name = "test2")
        )
    }

    @Test
    @DisplayName("hashcode() 로직 테스트")
    fun hashcodeLogicTest() {
        val tag = Tag()
        assertEquals(tag.id.hashCode(), tag.hashCode())
    }
}