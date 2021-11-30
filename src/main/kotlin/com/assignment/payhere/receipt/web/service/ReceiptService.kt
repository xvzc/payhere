package com.assignment.payhere.receipt.web.service

import com.assignment.payhere._global.error.AuthenticationFailedException
import com.assignment.payhere._global.error.DataFormatViolatedExceiption
import com.assignment.payhere._global.error.ErrorCode
import com.assignment.payhere._global.error.ResourceNotFoundException
import com.assignment.payhere.receipt.domain.dto.*
import com.assignment.payhere.receipt.domain.entity.Receipt
import com.assignment.payhere.receipt.web.repository.ReceiptQueryRepository
import com.assignment.payhere.receipt.web.repository.ReceiptRepository
import com.assignment.payhere.tag.web.repository.TagRepository
import com.assignment.payhere.user.web.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.YearMonth
import javax.transaction.Transactional


@Service
class ReceiptService(
    val receiptQueryRepository: ReceiptQueryRepository,
    val receiptRepository: ReceiptRepository,
    val tagRepository: TagRepository,
    val userRepository: UserRepository
) {
    fun getMonthlySumReceipts(userId: Long, yearMonth: String): List<ReceiptSumResponseDTO> {
        return receiptQueryRepository.findMonthlyReceipts(userId, yearMonth)
            .map { projection ->
                ReceiptSumResponseDTO.of(projection)
        }
    }

    fun getDailySimpleReceipts(userId: Long, date: String): List<ReceiptSimpleResponseDTO> {
        val res = receiptQueryRepository.findDailyReceipts(userId, date)

        return res.map { projection ->
                ReceiptSimpleResponseDTO.of(projection)
        }
    }

    fun getReceiptDetail(userId: Long, receiptId: Long): ReceiptDetailResponseDTO {
        val receipt = receiptRepository.findById(receiptId).orElseThrow {
            ResourceNotFoundException(ErrorCode.RECEIPT_NOT_FOUND)
        }

        val user = userRepository.findById(userId).orElseThrow {
            AuthenticationFailedException(ErrorCode.INVALID_LOGIN_INFO)
        }

        if(!receipt.isOwnedBy(user))
            throw AuthenticationFailedException(ErrorCode.ACCESS_DENIED)

        return ReceiptDetailResponseDTO.of(receipt)
    }

    fun getDeletedReceipts(userId: Long): List<ReceiptSimpleResponseDTO> {
        return receiptQueryRepository.findDeletedReceipts(userId).map { receipt ->
           ReceiptSimpleResponseDTO.of(receipt)
        }
    }

    @Transactional
    fun addReceipt(userId: Long, dto: ReceiptAddRequestDTO): ReceiptSimpleResponseDTO {
        val tag = tagRepository.findById(dto.tagId).orElseThrow {
            ResourceNotFoundException(ErrorCode.TAG_NOT_FOUND)
        }

        val user = userRepository.findById(userId).orElseThrow {
            AuthenticationFailedException(ErrorCode.INVALID_LOGIN_INFO)
        }

        if(!tag.isOwnedBy(user))
            throw AuthenticationFailedException(ErrorCode.ACCESS_DENIED)

        val receipt = Receipt(
                user = user,
                tag = tag,
                amount = dto.amount,
                description = dto.description
            )

        return ReceiptSimpleResponseDTO.of(receiptRepository.save(receipt))
    }

    @Transactional
    fun updateReceipt(userId:Long, receiptId: Long, dto: ReceiptUpdateRequestDTO): ReceiptSimpleResponseDTO {
        val receipt = receiptRepository.findById(receiptId).orElseThrow {
            ResourceNotFoundException(ErrorCode.RECEIPT_NOT_FOUND)
        }

        val user = userRepository.findById(userId).orElseThrow {
            AuthenticationFailedException(ErrorCode.INVALID_LOGIN_INFO)
        }

        if(!receipt.isOwnedBy(user))
            throw AuthenticationFailedException(ErrorCode.ACCESS_DENIED)

        val newTag = tagRepository.findById(dto.tagId).orElseThrow {
            ResourceNotFoundException(ErrorCode.TAG_NOT_FOUND)
        }

        if(!newTag.isOwnedBy(user))
            throw AuthenticationFailedException(ErrorCode.ACCESS_DENIED)

        val newReceipt = receipt.apply {
            amount = dto.amount
            tag = newTag
            description = dto.description
        }

        return ReceiptSimpleResponseDTO.of(newReceipt)
    }

    @Transactional
    fun deleteReceipt(userId: Long, receiptId: Long) {
        val receipt = receiptRepository.findById(receiptId).orElseThrow {
            ResourceNotFoundException(ErrorCode.RECEIPT_NOT_FOUND)
        }

        val user = userRepository.findById(userId).orElseThrow {
            AuthenticationFailedException(ErrorCode.INVALID_LOGIN_INFO)
        }

        if(!receipt.isOwnedBy(user))
            throw AuthenticationFailedException(ErrorCode.ACCESS_DENIED)

        receipt.apply {
            deleted = 'Y'
        }
    }

    @Transactional
    fun recoverReceipt(userId: Long, receiptId: Long) {
        val receipt = receiptRepository.findById(receiptId).orElseThrow {
            ResourceNotFoundException(ErrorCode.RECEIPT_NOT_FOUND)
        }

        val user = userRepository.findById(userId).orElseThrow {
            AuthenticationFailedException(ErrorCode.INVALID_LOGIN_INFO)
        }

        if(!receipt.isOwnedBy(user))
            throw AuthenticationFailedException(ErrorCode.ACCESS_DENIED)

        receipt.apply {
            deleted = 'N'
        }
    }
}