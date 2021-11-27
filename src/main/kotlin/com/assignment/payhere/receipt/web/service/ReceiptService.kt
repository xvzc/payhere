package com.assignment.payhere.receipt.web.service

import com.assignment.payhere._global.error.AuthenticationFailedException
import com.assignment.payhere._global.error.ErrorCode
import com.assignment.payhere._global.error.ResourceNotFoundException
import com.assignment.payhere._global.util.Constant
import com.assignment.payhere.receipt.domain.dto.*
import com.assignment.payhere.receipt.domain.entity.Receipt
import com.assignment.payhere.receipt.web.repository.ReceiptQueryRepository
import com.assignment.payhere.receipt.web.repository.ReceiptRepository
import com.assignment.payhere.tag.web.repository.TagRepository
import com.assignment.payhere.user.web.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class ReceiptService(
    val receiptQueryRepository: ReceiptQueryRepository,
    val receiptRepository: ReceiptRepository,
    val tagRepository: TagRepository,
    val userRepository: UserRepository
) {
    fun getMonthlySumReceipts(userId: Long, month: String): List<ReceiptSumResponseDTO> {
        // 해당 달의 첫번째 날짜를 OffsetDate로
        val firstDateOfMonth = OffsetDateTime.parse(month + "-01" + "T00:00" + Constant.TIME_ZONE)

        return receiptQueryRepository.findMonthlyReceipts(userId, firstDateOfMonth)
            .map { projection ->
                ReceiptSumResponseDTO.of(projection)
        }
    }

    fun getDailySimpleReceipts(userId: Long, date: String): List<ReceiptSimpleResponseDTO> {
        val offsetDateTime = OffsetDateTime.parse(date + "T00:00" + Constant.TIME_ZONE)

        return receiptQueryRepository.findDailyReceipts(userId, offsetDateTime)
            .map { projection ->
                ReceiptSimpleResponseDTO.of(projection)
        }
    }

    fun getReceiptDetail(userId: Long, receiptId: Long): ReceiptDetailResponseDTO {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw AuthenticationFailedException(ErrorCode.INVALID_LOGIN_INFO)

        val receipt = receiptRepository.findByIdOrNull(receiptId)
            ?: throw ResourceNotFoundException(ErrorCode.RECEIPT_NOT_FOUND)

        if(!receipt.isOwnedBy(user))
            throw AuthenticationFailedException(ErrorCode.ACCESS_DENIED)

        return ReceiptDetailResponseDTO.of(receipt)
    }

    fun addReceipt(userId: Long, dto: ReceiptAddRequestDTO): ReceiptSimpleResponseDTO {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw AuthenticationFailedException(ErrorCode.INVALID_LOGIN_INFO)

        val tag = tagRepository.findByIdOrNull(dto.tagId)
            ?: throw ResourceNotFoundException(ErrorCode.TAG_NOT_FOUND)

        if(!tag.isOwnedBy(user))
            throw AuthenticationFailedException(ErrorCode.ACCESS_DENIED)

        val receipt = receiptRepository.save(
            Receipt(
                user = user,
                tag = tag,
                amount = dto.amount,
                description = dto.description
            )
        )

        return ReceiptSimpleResponseDTO.of(receipt)
    }

    fun updateReceipt(dto: ReceiptUpdateRequestDTO): ReceiptDetailResponseDTO? {
        return null
    }

    fun deleteReceipt(ReceiptId: Long) {
    }
}