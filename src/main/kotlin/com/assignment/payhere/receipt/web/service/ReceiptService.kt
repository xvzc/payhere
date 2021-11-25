package com.assignment.payhere.receipt.web.service

import com.assignment.payhere.receipt.domain.dto.ReceiptAddRequestDTO
import com.assignment.payhere.receipt.domain.dto.ReceiptDetailResponseDTO
import com.assignment.payhere.receipt.domain.dto.ReceiptSimpleResponseDTO
import com.assignment.payhere.receipt.domain.dto.ReceiptUpdateRequestDTO
import org.springframework.stereotype.Service

@Service
class ReceiptService {
    fun getSimpleReceipt(): List<ReceiptSimpleResponseDTO>? {
        return null;
    }

    fun getReceiptDetail(): ReceiptDetailResponseDTO? {
        return null;
    }

    fun addReceipt(dto: ReceiptAddRequestDTO): ReceiptSimpleResponseDTO? {
        return null;
    }

    fun updateReceipt(dto: ReceiptUpdateRequestDTO): ReceiptDetailResponseDTO? {
        return null;
    }

    fun deleteReceipt(ReceiptId: Long) {
    }
}