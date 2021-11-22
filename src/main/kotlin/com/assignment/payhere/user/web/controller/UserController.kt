package com.assignment.payhere.user.web.controller

import com.assignment.payhere.user.web.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {
    @GetMapping("/{user_id}")
    fun testUser(
        @PathVariable("user_id") userId: Long
    ): Long {
        return userId;
    }
}