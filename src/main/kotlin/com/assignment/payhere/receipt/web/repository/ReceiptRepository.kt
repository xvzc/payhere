package com.assignment.payhere.receipt.web.repository

import com.assignment.payhere.receipt.domain.entity.Receipt
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReceiptRepository: JpaRepository<Receipt, Long> {
}