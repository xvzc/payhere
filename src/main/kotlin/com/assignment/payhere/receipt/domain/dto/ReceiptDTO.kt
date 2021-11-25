package com.assignment.payhere.receipt.domain.dto


data class ReceiptAddRequestDTO(
    val amount: Int = 0,
    val tag: String = "",
    val description: String = ""
)

data class ReceiptUpdateRequestDTO(
    val amount: Int = 0,
    val tag: String = "",
    val description: String = ""
)

data class ReceiptSimpleResponseDTO(
    val id: Long = 0,
    val amount: Int = 0,
    val tag: String = "",
)

data class ReceiptDetailResponseDTO(
    val id: Long = 0,
    val amount: Int = 0,
    val tag: String = "",
    val description: String = ""
)