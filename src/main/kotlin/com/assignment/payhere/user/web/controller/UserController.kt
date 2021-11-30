package com.assignment.payhere.user.web.controller

import com.assignment.payhere._global.annotaion.LoginData
import com.assignment.payhere._global.dto.UnitResponse
import com.assignment.payhere._global.util.jwt.PayhereToken
import com.assignment.payhere.user.domain.dto.UserResponseDTO
import com.assignment.payhere.user.web.service.UserService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService
) {
    @GetMapping("/")
    @ApiOperation(value = "유저 정보 조회", notes = "로그인된 유저의 정보를 조회합니다.")
    fun getUser(@LoginData jwt: PayhereToken): UnitResponse<UserResponseDTO> {
        return UnitResponse(
            data = userService.getUser(jwt.userId)
        )
    }
}