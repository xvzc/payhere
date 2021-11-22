package com.assignment.payhere.cashbook.web.repository

import com.assignment.payhere.user.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CashbookRepository: JpaRepository<User, Long> {
}