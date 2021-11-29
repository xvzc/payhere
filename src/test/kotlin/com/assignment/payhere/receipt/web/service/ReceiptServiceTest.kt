package com.assignment.payhere.receipt.web.service

import com.assignment.payhere._global.error.AuthenticationFailedException
import com.assignment.payhere._global.error.ErrorCode
import com.assignment.payhere.receipt.domain.dto.ReceiptSimpleProjection
import com.assignment.payhere.receipt.domain.dto.ReceiptSumProjection
import com.assignment.payhere.receipt.domain.entity.Receipt
import com.assignment.payhere.receipt.web.repository.ReceiptQueryRepository
import com.assignment.payhere.receipt.web.repository.ReceiptRepository
import com.assignment.payhere.tag.web.repository.TagRepository
import com.assignment.payhere.user.domain.entity.User
import com.assignment.payhere.user.web.repository.UserRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.Assert.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.*
import java.time.OffsetDateTime
import java.util.*

@ExtendWith(MockKExtension::class)
class ReceiptServiceTest {
    @InjectMockKs private lateinit var receiptService: ReceiptService
    @RelaxedMockK private lateinit var receiptRepository: ReceiptRepository
    @RelaxedMockK private lateinit var receiptQueryRepository: ReceiptQueryRepository
    @RelaxedMockK private lateinit var tagRepository: TagRepository
    @RelaxedMockK private lateinit var userRepository: UserRepository

    @Test
    @DisplayName("getMonthlySumReceipts() 비즈니스 로직 테스트")
    fun getMonthlySumReceiptsSuccessTest() {
        val receiptSumProjection = ReceiptSumProjection(
            date = "2021-11-01",
            income = 1000,
            outgo = 1000
        )

        val receiptSumProjections = arrayListOf<ReceiptSumProjection>(
            receiptSumProjection,
            receiptSumProjection,
            receiptSumProjection
        )

        every {
            receiptQueryRepository
                .findMonthlyReceipts(
                    anyLong(),
                    any() as OffsetDateTime
                )
        } answers {
            receiptSumProjections
        }

        val month = "2021-11"
        val res = receiptService.getMonthlySumReceipts(
            anyLong(),
            month
        )

        res.forEach { dto ->
            assertTrue(dto.date.startsWith(month))
            assertEquals(dto.income, receiptSumProjection.income)
            assertEquals(dto.outgo, receiptSumProjection.outgo)
        }
    }

    @Test
    @DisplayName("getDailySimpleReceipts() 비즈니스 로직 테스트")
    fun getDailySimpleReceiptsSuccessTest() {
        val receiptSimpleProjection = ReceiptSimpleProjection(
            id = 0,
            date = "2021-11-01",
            amount = 0,
            tag = "tag"
        )

        val receiptSimpleProjections = arrayListOf<ReceiptSimpleProjection>(
            receiptSimpleProjection,
            receiptSimpleProjection,
            receiptSimpleProjection
        )

        every {
            receiptQueryRepository
                .findDailyReceipts(
                    anyLong(),
                    any() as OffsetDateTime
                )
        } answers {
            receiptSimpleProjections
        }

        val date = "2021-11-01"
        receiptService.getDailySimpleReceipts(anyLong(), date).forEach { each ->
            assertEquals(each.date, receiptSimpleProjection.date)
            assertEquals(each.id, receiptSimpleProjection.id)
            assertEquals(each.amount, receiptSimpleProjection.amount)
            assertEquals(each.tag, receiptSimpleProjection.tag)
        }
    }

    @Test
    @DisplayName("getReceiptDetail() 비즈니스 로직 테스트")
    fun getReceiptDetailSuccessTest() {
        val user = User()
        val receipt = Receipt(
            user = user
        )

        every {
            receiptRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(receipt)
        }

        val res = receiptService.getReceiptDetail(anyLong(), anyLong())
        assertEquals(res.id, receipt.id)
        assertEquals(res.amount, receipt.amount)
        assertEquals(res.date, receipt.created.toLocalDateTime().toString())
        assertEquals(res.tag, receipt.tag.name)
    }

    @Test
    @DisplayName("getReceiptDetail() 세션 ID에 해당하는 유저 존재하지 않을 때")
    fun getReceiptDetailTestWhenUserNotFound() {
        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(null)
        }

        assertThrows<AuthenticationFailedException> {
            receiptService.getReceiptDetail(anyLong(), anyLong())
        }
    }

    @Test
    @DisplayName("getReceiptDetail() receiptId 해당하는 기록 존재하지 않을 때")
    fun getReceiptDetailReceiptNotFoundTest() {
        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(null)
        }

        assertThrows<AuthenticationFailedException> {
            receiptService.getReceiptDetail(anyLong(), anyLong())
        }
    }

    @Test
    @DisplayName("getDeletedReceipts() 비즈니스 로직 테스트")
    fun getDeletedReceiptsTest() {
        val receipt = Receipt()
        val receipts = arrayListOf(
            receipt,
            receipt,
            receipt
        )

        every {
            receiptQueryRepository.findDeletedReceipts(anyLong())
        } answers {
            receipts
        }

        receiptService.getDeletedReceipts(anyLong()).forEach { each ->
            assertEquals(receipt.id, each.id)
            assertEquals(receipt.tag.name, each.tag)
            assertEquals(receipt.deleted, 'N')
            assertEquals(receipt.amount, each.amount)
            assertEquals(receipt.created.toLocalDate().toString(), each.date)
        }
    }

    @Test
    fun addReceiptTest() {
    }

    @Test
    fun updateReceiptTest(){
    }

    @Test
    fun deleteReceiptTest() {
    }

    @Test
    fun recoverReceiptTest() {
    }
}