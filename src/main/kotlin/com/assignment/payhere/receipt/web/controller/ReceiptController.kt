package com.assignment.payhere.receipt.web.controller

import com.assignment.payhere.receipt.domain.dto.ReceiptAddRequestDTO
import com.assignment.payhere.receipt.domain.dto.ReceiptUpdateRequestDTO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/receipts")
class ReceiptController {
    @GetMapping("/")
    fun getMonthlySimpleReceipts(@RequestParam(name = "year_month") yearMonth: String) {
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