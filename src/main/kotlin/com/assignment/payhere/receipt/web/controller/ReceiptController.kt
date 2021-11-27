package com.assignment.payhere.receipt.web.controller

import com.assignment.payhere._global.annotaion.SessionData
import com.assignment.payhere._global.dto.ListResponse
import com.assignment.payhere.receipt.domain.dto.ReceiptAddRequestDTO
import com.assignment.payhere.receipt.domain.dto.ReceiptSimpleResponseDTO
import com.assignment.payhere.receipt.domain.dto.ReceiptSumResponseDTO
import com.assignment.payhere.receipt.domain.dto.ReceiptUpdateRequestDTO
import com.assignment.payhere.receipt.web.service.ReceiptService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/receipts")
class ReceiptController(
    val receiptService: ReceiptService
) {
    @GetMapping("/monthly")
    fun getMonthlySumReceipts(
        @SessionData userId: Long,
        @RequestParam(name = "month") month: String
    ): ListResponse<ReceiptSumResponseDTO> {
        return ListResponse(
            data = receiptService.getMonthlySumReceipts(userId, month)
        )
    }

    @GetMapping("/daily")
    fun getDailySimpleReceipts(
        @SessionData userId: Long,
        @RequestParam(name = "year_month_date") date: String
    ): ListResponse<ReceiptSimpleResponseDTO> {
        return ListResponse(
            data = receiptService.getDailySimpleReceipts(userId, date)
        )
    }

    @GetMapping("/{receipt_id}")
    fun getReceiptDetail(@PathVariable(name = "receipt_id") receiptId: Long) {
    }

    @PostMapping("")
    fun addReceipt(@RequestBody dto: ReceiptAddRequestDTO) {
    }

    @PutMapping("{receipt_id}")
    fun updateReceipt(
        @PathVariable(name = "receipt_id") receiptId: Long,
        @RequestBody dto: ReceiptUpdateRequestDTO
    ) {
    }

    @DeleteMapping("{receipt_id}")
    fun deleteReceipt(@PathVariable(name = "receipt_id") receiptId: Long) {
    }
}