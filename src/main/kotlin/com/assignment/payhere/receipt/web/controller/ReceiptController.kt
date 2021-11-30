package com.assignment.payhere.receipt.web.controller

import com.assignment.payhere._global.annotaion.LoginData
import com.assignment.payhere._global.dto.ListResponse
import com.assignment.payhere._global.dto.UnitResponse
import com.assignment.payhere._global.util.jwt.PayhereToken
import com.assignment.payhere.receipt.domain.dto.*
import com.assignment.payhere.receipt.web.service.ReceiptService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/receipts")
class ReceiptController(
    val receiptService: ReceiptService
) {
    @GetMapping("/monthly")
    @ApiOperation(value = "가계부 달력 조회", notes = "해당 월의 날짜별 수입과 지출 리스트를 조회합니다.")
    fun getMonthlySumReceipts(
        @LoginData jwt: PayhereToken,
        dto: ReceiptGetMonthlySumRequestDTO
    ): ListResponse<ReceiptSumResponseDTO> {
        return ListResponse(
            data = receiptService.getMonthlySumReceipts(jwt.userId, dto.yearMonth)
        )
    }

    @GetMapping("/daily")
    @ApiOperation(value = "가계부 일별 조회", notes = "해당 날짜의 수입과 지출의 간소화된 정보를 리스트로 조회합니다.")
    fun getDailySimpleReceipts(
        @LoginData jwt: PayhereToken,
        dto: ReceiptGetDailySimpleRequestDTO
    ): ListResponse<ReceiptSimpleResponseDTO> {
        return ListResponse(
            data = receiptService.getDailySimpleReceipts(jwt.userId, dto.date)
        )
    }

    @GetMapping("/{receipt_id}")
    @ApiOperation(value = "기록 상세 조회", notes = "특정 기록의 상세 조회를 수행합니다.")
    fun getReceiptDetail(
        @LoginData jwt: PayhereToken,
        @PathVariable(name = "receipt_id") receiptId: Long
    ): UnitResponse<ReceiptDetailResponseDTO> {
        return UnitResponse(
            data = receiptService.getReceiptDetail(jwt.userId, receiptId)
        )
    }

    @GetMapping("/deleted")
    @ApiOperation(value = "삭제된 기록 조회", notes = "삭제된 기록들의 조회를 수행합니다.")
    fun getDeletedReceipts(
        @LoginData jwt: PayhereToken
    ): ListResponse<ReceiptSimpleResponseDTO> {
        return ListResponse(
            data = receiptService.getDeletedReceipts(jwt.userId)
        )
    }

    @PostMapping("")
    @ApiOperation(value = "가계부 기록 추가", notes = "가계부 기록을 추가합니다.")
    fun addReceipt(
        @LoginData jwt: PayhereToken,
        @RequestBody dto: ReceiptAddRequestDTO
    ): UnitResponse<ReceiptSimpleResponseDTO> {
        return UnitResponse(
            data = receiptService.addReceipt(jwt.userId, dto)
        )
    }

    @PutMapping("/{receipt_id}")
    @ApiOperation(value = "가계부 기록 수정", notes = "가계부 기록을 수정합니다.")
    fun updateReceipt(
        @LoginData jwt: PayhereToken,
        @PathVariable(name = "receipt_id") receiptId: Long,
        @RequestBody dto: ReceiptUpdateRequestDTO
    ): UnitResponse<ReceiptSimpleResponseDTO> {
        return UnitResponse(
            data = receiptService.updateReceipt(jwt.userId, receiptId, dto)
        )
    }

    @DeleteMapping("/{receipt_id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "가계부 기록 삭제", notes = "가계부 기록을 삭제합니다.")
    fun deleteReceipt(
        @LoginData jwt: PayhereToken,
        @PathVariable(name = "receipt_id") receiptId: Long
    ) {
        receiptService.deleteReceipt(jwt.userId, receiptId)
    }

    @PatchMapping("/{receipt_id}/recovery")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "가계부 삭제 내역 복구 ", notes = "가계부에서 삭제된 기록을 복구합니다.")
    fun recoverReceipt(
        @LoginData jwt: PayhereToken,
        @PathVariable(name = "receipt_id") receiptId: Long
    ) {
        receiptService.recoverReceipt(jwt.userId, receiptId)
    }
}