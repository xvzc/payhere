package com.assignment.payhere.receipt.web.service

import com.assignment.payhere._global.error.AuthenticationFailedException
import com.assignment.payhere._global.error.ResourceNotFoundException
import com.assignment.payhere.receipt.domain.dto.ReceiptAddRequestDTO
import com.assignment.payhere.receipt.domain.dto.ReceiptSimpleProjection
import com.assignment.payhere.receipt.domain.dto.ReceiptSumProjection
import com.assignment.payhere.receipt.domain.dto.ReceiptUpdateRequestDTO
import com.assignment.payhere.receipt.domain.entity.Receipt
import com.assignment.payhere.receipt.web.repository.ReceiptQueryRepository
import com.assignment.payhere.receipt.web.repository.ReceiptRepository
import com.assignment.payhere.tag.domain.entity.Tag
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
import java.time.LocalDate
import java.time.YearMonth
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
    fun getMonthlySumReceiptsLogicTest() {
        val receiptSumProjection = ReceiptSumProjection(
            date = "2021-11-01",
            income = anyLong(),
            outgo = anyLong()
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
                    any()
                )
        } answers {
            receiptSumProjections
        }

        val res = receiptService.getMonthlySumReceipts(
            anyLong(),
            anyString()
        )

        res.forEach { dto ->
            assertEquals(dto.income, receiptSumProjection.income)
            assertEquals(dto.outgo, receiptSumProjection.outgo)
        }
    }

    @Test
    @DisplayName("getDailySimpleReceipts() 비즈니스 로직 테스트")
    fun getDailySimpleReceiptsLogicTest() {
        val receiptSimpleProjection = ReceiptSimpleProjection(
            id = anyLong(),
            dateTime = "2021-11-01",
            amount = anyInt(),
            tag = anyString()
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
                    any()
                )
        } answers {
            receiptSimpleProjections
        }

        receiptService.getDailySimpleReceipts(anyLong(), anyString()).forEach { each ->
            assertEquals(receiptSimpleProjection.dateTime, each.date)
            assertEquals(receiptSimpleProjection.id, each.id)
            assertEquals(receiptSimpleProjection.amount, each.amount)
            assertEquals(receiptSimpleProjection.tag, each.tag)
        }
    }

    @Test
    @DisplayName("getReceiptDetail() 비즈니스 로직 테스트")
    fun getReceiptDetailLogicTest() {
        val user = User()
        val receipt = Receipt(
            user = user
        )

        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(user)
        }

        every {
            receiptRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(receipt)
        }

        val res = receiptService.getReceiptDetail(anyLong(), anyLong())

        assertEquals(receipt.id, res.id)
        assertEquals(receipt.amount, res.amount)
        assertEquals(receipt.created.toLocalDateTime().toString(), res.date)
        assertEquals(receipt.tag.id, res.tag.id)
        assertEquals(receipt.tag.id, res.tag.id)
    }

    @Test
    @DisplayName("getReceiptDetail()  해당하는 유저 존재하지 않을 때")
    fun getReceiptDetailTestWhenUserNotFound() {
        every {
            receiptRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(Receipt())
        }

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
    fun getReceiptDetailWhenReceiptNotFoundTest() {
        every {
            receiptRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(null)
        }

        assertThrows<ResourceNotFoundException> {
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
    @DisplayName("addReceipt() 비즈니스 로직 테스트")
    fun addReceiptLogicTest() {
        val user = User()
        val tag = Tag(user = user)
        val receipt = Receipt(user = user)

        every {
            tagRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(tag)
        }

        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(user)
        }

        every {
            hint(Receipt::class)
            receiptRepository.save(any())
        } answers {
            receipt
        }

        val res = receiptService.addReceipt(anyLong(), ReceiptAddRequestDTO(
            anyInt(),
            anyLong(),
            anyString()
        ))

        assertEquals(receipt.id, res.id)
        assertEquals(receipt.amount, res.amount)
        assertEquals(receipt.created.toLocalDate().toString(), res.date)
        assertEquals(receipt.tag.name, res.tag)
    }

    @Test
    @DisplayName("updateReceipt() 비즈니스 로직 테스트")
    fun updateReceiptLogicTest() {
        val receiptId = anyLong()
        val userId = anyLong()
        val updateDTO = ReceiptUpdateRequestDTO(
            amount = anyInt(),
            tagId = anyLong()
        )

        val user = User(id = userId)
        val tag = Tag(
            id = updateDTO.tagId,
            user = user
        )
        val receipt = Receipt(
            user = user,
            amount = updateDTO.amount,
            tag = tag
        )

        every {
            tagRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(tag)
        }

        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(user)
        }

        every {
            receiptRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(receipt)
        }

        val res = receiptService.updateReceipt(userId, receiptId, updateDTO)

        assertEquals(tag.name, res.tag)
        assertEquals(receiptId, res.id)
        assertEquals(updateDTO.amount, res.amount)
        assertEquals(receipt.created.toLocalDate().toString(), res.date)
    }
    @Test
    @DisplayName("updateReceipt() receiptId에 해당하는 기록이 존재하지 않을 때")
    fun updateReceiptWhenReceiptNotFoundTest() {
        val updateDTO = ReceiptUpdateRequestDTO(
            amount = anyInt(),
            tagId = anyLong()
        )

        every {
            receiptRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(null)
        }

        assertThrows<ResourceNotFoundException> {
            receiptService.updateReceipt(anyLong(), anyLong(), updateDTO)
        }
    }

    @Test
    @DisplayName("updateReceipt()  해당하는 유저가 존재하지 않을 때")
    fun updateReceiptWhenUserNotFoundTest() {
        val updateDTO = ReceiptUpdateRequestDTO(
            amount = anyInt(),
            tagId = anyLong()
        )

        every {
            receiptRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(Receipt())
        }

        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(null)
        }

        assertThrows<AuthenticationFailedException> {
            receiptService.updateReceipt(anyLong(), anyLong(), updateDTO)
        }
    }


    @Test
    @DisplayName("updateReceipt() receiptId에 해당하는 기록이 유저의 소유가 아닐 때")
    fun updateReceiptWhenReceiptNotOwnedTest() {
        val owner = User(id = 0)
        val other = User(id = 1)
        val receipt = Receipt(user = owner)

        val updateDTO = ReceiptUpdateRequestDTO(
            amount = anyInt(),
            tagId = anyLong()
        )

        every {
            receiptRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(receipt)
        }

        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(other)
        }

        assertThrows<AuthenticationFailedException> {
            receiptService.updateReceipt(anyLong(), anyLong(), updateDTO)
        }
    }

    @Test
    @DisplayName("updateReceipt() 수정하기위해 입력받은 태그가 존재하지 않을 때")
    fun updateReceiptWhenTagNotFoundTest() {
        val updateDTO = ReceiptUpdateRequestDTO(
            amount = anyInt(),
            tagId = anyLong()
        )

        every {
            receiptRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(Receipt())
        }

        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(User())
        }

        every {
            tagRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(null)
        }

        assertThrows<ResourceNotFoundException> {
            receiptService.updateReceipt(anyLong(), anyLong(), updateDTO)
        }
    }

    @Test
    @DisplayName("updateReceipt() 태그가 유저의 소유가 아닐 때")
    fun updateReceiptWhenTagNotOwnedTest() {
        val updateDTO = ReceiptUpdateRequestDTO(
            amount = anyInt(),
            tagId = anyLong()
        )

        val owner = User(id = 0)
        val other = User(id = 1)
        val tag = Tag(user = owner)

        every {
            receiptRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(Receipt())
        }

        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(other)
        }

        every {
            tagRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(tag)
        }

        assertThrows<AuthenticationFailedException> {
            receiptService.updateReceipt(anyLong(), anyLong(), updateDTO)
        }
    }

    @Test
    @DisplayName("deleteReceipt() 비즈니스 로직 테스트")
    fun deleteReceiptLogicTest() {
        /** No return */
    }

    @Test
    @DisplayName("deleteReceipt() receiptId에 해당하는 기록이 없을 때")
    fun deleteReceiptWhenReceiptNotFoundTest() {
        every {
            receiptRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(null)
        }

        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(User())
        }

        assertThrows<ResourceNotFoundException> {
            receiptService.deleteReceipt(anyLong(), anyLong())
        }
    }

    @Test
    @DisplayName("deleteReceipt()  해당하는 기록이 없을 때")
    fun deleteReceiptWhenUserNotFoundTest() {
        every {
            receiptRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(Receipt())
        }

        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(null)
        }

        assertThrows<AuthenticationFailedException> {
            receiptService.deleteReceipt(anyLong(), anyLong())
        }
    }

    @Test
    @DisplayName("deleteReceipt() 해당 기록이 유저의 소유가 아닐 때")
    fun deleteReceiptWhenReceiptNotOwnedTest() {
        val owner = User(id = 0)
        val other = User(id = 1)
        val receipt = Receipt(user = owner)
        every {
            receiptRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(receipt)
        }

        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(other)
        }

        assertThrows<AuthenticationFailedException> {
            receiptService.deleteReceipt(anyLong(), anyLong())
        }
    }

    @Test
    @DisplayName("recoverReceipt() 비즈니스 로직 테스트")
    fun recoverReceiptTest() {
        /** No return */
    }

    @Test
    @DisplayName("recoverReceipt() receiptId에 해당하는 기록이 없을 때")
    fun recoverReceiptWhenReceiptNotFoundTest() {
        every {
            receiptRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(null)
        }

        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(User())
        }

        assertThrows<ResourceNotFoundException> {
            receiptService.recoverReceipt(anyLong(), anyLong())
        }
    }

    @Test
    @DisplayName("recoverReceipt()  해당하는 기록이 없을 때")
    fun recoverReceiptWhenUserNotFoundTest() {
        every {
            receiptRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(Receipt())
        }

        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(null)
        }

        assertThrows<AuthenticationFailedException> {
            receiptService.recoverReceipt(anyLong(), anyLong())
        }
    }

    @Test
    @DisplayName("recoverReceipt() 해당 기록이 유저의 소유가 아닐 때")
    fun recoverReceiptWhenReceiptNotOwnedTest() {
        val owner = User(id = 0)
        val other = User(id = 1)
        val receipt = Receipt(user = owner)
        every {
            receiptRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(receipt)
        }

        every {
            userRepository.findById(anyLong())
        } answers {
            Optional.ofNullable(other)
        }

        assertThrows<AuthenticationFailedException> {
            receiptService.recoverReceipt(anyLong(), anyLong())
        }
    }
}