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

data class ReceiptSumResponseDTO(
    val date: String = "",
    val income: Long = 0,
    val outgo: Long = 0,
) {
    companion object {
        fun of(projection: ReceiptSumProjection): ReceiptSumResponseDTO {
            return ReceiptSumResponseDTO(
                date = projection.date,
                income = projection.income,
                outgo = projection.outgo
            )
        }
    }
}

data class ReceiptSimpleResponseDTO(
    val id: Long = 0,
    val date: String = "",
    val amount: Int = 0,
    val tag: String = "",
) {
    companion object {
        fun of(projection: ReceiptSimpleProjection): ReceiptSimpleResponseDTO {
            return ReceiptSimpleResponseDTO(
                id = projection.id,
                date = projection.date,
                amount = projection.amount,
                tag = projection.tag
            )
        }
    }
}

data class ReceiptDetailResponseDTO(
    val id: Long = 0,
    val amount: Int = 0,
    val tag: String = "",
    val description: String = ""
) {

}
