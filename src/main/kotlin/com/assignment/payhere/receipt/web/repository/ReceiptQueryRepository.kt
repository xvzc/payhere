package com.assignment.payhere.receipt.web.repository

import com.assignment.payhere.receipt.domain.dto.QReceiptSimpleProjection
import com.assignment.payhere.receipt.domain.dto.QReceiptSumProjection
import com.assignment.payhere.receipt.domain.dto.ReceiptSimpleProjection
import com.assignment.payhere.receipt.domain.dto.ReceiptSumProjection
import com.assignment.payhere.receipt.domain.entity.QReceipt
import com.querydsl.core.types.ConstantImpl
import com.querydsl.core.types.dsl.CaseBuilder
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime


@Repository
class ReceiptQueryRepository(
    val queryFactory: JPAQueryFactory
) {
    fun findMonthlyReceipts(userId: Long, firstDateOfMonth: OffsetDateTime): MutableList<ReceiptSumProjection> {
        val receipt = QReceipt.receipt

        val formattedDate = Expressions.stringTemplate(
            "DATE_FORMAT({0}, {1})",
            receipt.created,
            ConstantImpl.create("%Y-%m-%d")
        )

        val income = CaseBuilder()
            .`when`(receipt.amount.gt(0L)).then(receipt.amount)
            .otherwise(0)

        val outgo = CaseBuilder()
            .`when`(receipt.amount.lt(0L)).then(receipt.amount)
            .otherwise(0)

        return queryFactory
            .select(
                QReceiptSumProjection(
                    formattedDate,
                    income.longValue().sum(),
                    outgo.longValue().sum(),
                )
            )
            .from(receipt)
            .groupBy(formattedDate)
            .where(
                receipt.user.id.eq(userId)
                    .and(formattedDate.goe(firstDateOfMonth.toLocalDate().toString())) // 이번달 1일 부터
                    .and(formattedDate.lt(firstDateOfMonth.plusMonths(1).toLocalDate().toString())) // 다음달 이전까지
            )
            .fetch()
    }

    fun findDailyReceipts(userId: Long, date: OffsetDateTime): MutableList<ReceiptSimpleProjection> {
        val receipt = QReceipt.receipt

        val formattedDate = Expressions.stringTemplate(
            "DATE_FORMAT({0}, {1})",
            receipt.created,
            ConstantImpl.create("%Y-%m-%d")
        )

        return queryFactory
            .select(
                QReceiptSimpleProjection(
                    receipt.id,
                    formattedDate,
                    receipt.amount,
                    receipt.tag.name
                )
            )
            .from(receipt)
            .where(
                receipt.user.id.eq(userId)
                    .and(formattedDate.goe(date.toLocalDate().toString())) // 요청된 날짜 부터
                    .and(formattedDate.lt(date.plusDays(1).toLocalDate().toString())) // 그 다음달 이전까지
            )
            .fetch()

    }
}