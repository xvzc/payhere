package com.assignment.payhere.user.web.controller

import com.assignment.payhere.user.web.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService
) {
    @GetMapping("/{user_id}")
    fun getUser(@PathVariable("user_id") userId: Long): Long {
        return userId;
    }

//    @PostMapping("")
//    fun addUser(@RequestBody dto: UserAddRequestDTO): CheckResponse {
//        return CheckResponse(
//            success = userService.addUser(dto)
//        )
//    }
}