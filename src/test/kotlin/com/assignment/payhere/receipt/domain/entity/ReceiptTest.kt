package com.assignment.payhere.receipt.domain.entity

/** Essential imports for Unit tests Start */
import com.assignment.payhere.tag.domain.entity.Tag
import com.assignment.payhere.user.domain.entity.User
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
/** Essential imports for Unit tests End */


class ReceiptTest {
    @Test
    @DisplayName("isOwnedBy 로직 테스트")
    fun isOwnedByLogicTest() {
        val owner = User(id = 1)
        val other = User(id = 2)
        val receipt = Receipt(user = owner)

        assertTrue(receipt.isOwnedBy(owner))
        assertFalse(receipt.isOwnedBy(other))
    }

    @Test
    @DisplayName("equals() 같을 때")
    fun equalsWhenEqualsTest() {
        val receipt1 = Receipt(
            id = 1,
            user = User(),
            amount = 100,
            tag = Tag(),
            description = ""
        )

        val receipt2 = Receipt(
            id = 1,
            user = User(),
            amount = 100,
            tag = Tag(),
            description = ""
        )

        assertEquals(receipt1, receipt2)
    }

    @Test
    @DisplayName("equals() id가 다를 때")
    fun equalsWhenIdNotEqualsTest() {
        assertNotEquals(
            Receipt(id = 0),
            Receipt(id = 1)
        )
    }

    @Test
    @DisplayName("hashcode() 로직 테스트")
    fun hashcodeWhenEmailNotEqualsTest() {
        val receipt = Receipt()
        assertEquals(receipt.id.hashCode(), receipt.hashCode())
    }
}