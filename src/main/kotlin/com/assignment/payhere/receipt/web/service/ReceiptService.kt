package com.assignment.payhere.receipt.web.service

import com.assignment.payhere._global.util.Constant
import com.assignment.payhere.receipt.domain.dto.*
import com.assignment.payhere.receipt.web.repository.ReceiptQueryRepository
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class ReceiptService(
    val receiptQueryRepository: ReceiptQueryRepository
) {
    fun getMonthlySumReceipts(userId: Long, month: String): List<ReceiptSumResponseDTO> {
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

    fun getReceiptDetail(): ReceiptDetailResponseDTO? {
        return null;
    }

    fun addReceipt(dto: ReceiptAddRequestDTO): ReceiptSumResponseDTO? {
        return null;
    }

    fun updateReceipt(dto: ReceiptUpdateRequestDTO): ReceiptDetailResponseDTO? {
        return null;
    }

    fun deleteReceipt(ReceiptId: Long) {
    }
}