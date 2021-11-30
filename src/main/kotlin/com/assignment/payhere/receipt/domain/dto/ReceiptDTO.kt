package com.assignment.payhere.receipt.domain.dto

import com.assignment.payhere.receipt.domain.entity.Receipt
import com.assignment.payhere.tag.domain.dto.TagResponseDTO
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Range
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import kotlin.Int.Companion.MAX_VALUE
import kotlin.Int.Companion.MIN_VALUE

data class ReceiptGetMonthlySumRequestDTO(
    @Pattern(regexp = "^\\d{4}\\-(0?[1-9]|1[012])$", message = "날짜 형식(yyyy-MM)에 맞지 않습니다") // yyyy-MM만 받기 위한 정규표현식
    val yearMonth: String
)

data class ReceiptGetDailySimpleRequestDTO(
    @Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))", message = "날짜 형식(yyyy-MM)에 맞지 않습니다") // yyyy-MM만 받기 위한 정규표현식
    val date: String
)

data class ReceiptAddRequestDTO(
    @Range(min = MIN_VALUE.toLong(), max = MAX_VALUE.toLong())
    val amount: Int = 0,
    @Range(min = Long.MIN_VALUE, max = Long.MAX_VALUE)
    @NotNull(message = "태그를 입력해 주세요")
    val tagId: Long,
    val description: String = ""
)

data class ReceiptUpdateRequestDTO(
    @Range(min = MIN_VALUE.toLong(), max = MAX_VALUE.toLong())
    val amount: Int = 0,
    @Range(min = Long.MIN_VALUE, max = Long.MAX_VALUE)
    @NotNull(message = "태그를 입력해 주세요")
    val tagId: Long,
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
    val tag: String
) {
    companion object {
        fun of(projection: ReceiptSimpleProjection): ReceiptSimpleResponseDTO {
            return ReceiptSimpleResponseDTO(
                id = projection.id,
                date = projection.dateTime,
                amount = projection.amount,
                tag = projection.tag
            )
        }

        fun of(receipt: Receipt): ReceiptSimpleResponseDTO {
            return ReceiptSimpleResponseDTO(
                id = receipt.id,
                date = receipt.created.toLocalDate().toString(),
                amount = receipt.amount,
                tag = receipt.tag.name
            )
        }
    }
}

data class ReceiptDetailResponseDTO(
    val id: Long = 0,
    val date: String = "",
    val amount: Int = 0,
    val tag: TagResponseDTO ,
    val description: String = ""
) {
    companion object {
        fun of(receipt: Receipt): ReceiptDetailResponseDTO {
            return ReceiptDetailResponseDTO(
                id = receipt.id,
                date = receipt.created.toLocalDateTime().toString(),
                amount = receipt.amount,
                tag = TagResponseDTO.of(receipt.tag),
                description = receipt.description
            )
        }
    }
}
