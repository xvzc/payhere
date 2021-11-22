package com.assignment.payhere.cashbook.web.controller

import com.assignment.payhere.cashbook.web.service.CashbookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cashbook")
class CashbookController {
    @GetMapping("/{user_id}")
    fun getCashbook(@PathVariable("user_id") id: Long): Long {
        return id;
    }
}