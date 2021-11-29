package com.assignment.payhere.user.web.controller

import com.assignment.payhere._global.annotaion.SessionData
import com.assignment.payhere._global.dto.CheckDTO
import com.assignment.payhere._global.dto.UnitResponse
import com.assignment.payhere.user.domain.dto.SignInRequestDTO
import com.assignment.payhere.user.domain.dto.RegisterRequestDTO
import com.assignment.payhere.user.domain.dto.UserResponseDTO
import com.assignment.payhere.user.web.service.HomeService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid


@RestController
@RequestMapping("")
class HomeController(
    val homeService: HomeService
) {
    @PostMapping("/check-email")
    @ApiOperation(value = "이메일 중복 검사", notes = "가입할 때 사용할 이메일의 중복여부를 검사합니다.")
    fun checkEmail(@RequestBody email: String): UnitResponse<CheckDTO> {
        return UnitResponse(
            data = homeService.checkEmail(email)
        )
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "회원가입", notes = "유저를 생성합니다.")
    fun register(@Valid @RequestBody dto: RegisterRequestDTO): UnitResponse<UserResponseDTO> {
        return UnitResponse(
            data = homeService.register(dto)
        )
    }

    @PostMapping("/sign-in")
    @ApiOperation(value = "로그인", notes = "로그인을 수행합니다.")
    fun signIn(@RequestBody dto: SignInRequestDTO, request: HttpServletRequest): UnitResponse<UserResponseDTO> {
        return UnitResponse(
            data = homeService.signIn(dto, request.session)
        )
    }

    @DeleteMapping("/sign-out")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "로그아웃", notes = "로그아웃을 수행합니다.")
    fun signOut(request: HttpServletRequest) {
        if(request.session != null) request.session.invalidate()
    }
}