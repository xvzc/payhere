package com.assignment.payhere.receipt.domain.entity

/** Essential imports for Unit tests Start */
import com.assignment.payhere.user.domain.entity.User
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
/** Essential imports for Unit tests End */


class ReceiptTest {
    @Test
    fun isOwnedBy() {
        val owner = User(
            id = 1,
            email = "owner@gmail.com"
        )

        val other = User(
            id = 2,
            email = "other@gmail.com"
        )
        val receipt = Receipt(
            user = owner
        )

        assertTrue(receipt.isOwnedBy(owner))
        assertFalse(receipt.isOwnedBy(other))
    }
}