package com.assignment.payhere.user.web.controller

import com.assignment.payhere._global.annotaion.SessionData
import com.assignment.payhere._global.dto.CheckDTO
import com.assignment.payhere._global.dto.UnitResponse
import com.assignment.payhere.user.domain.dto.SignInRequestDTO
import com.assignment.payhere.user.domain.dto.RegisterRequestDTO
import com.assignment.payhere.user.domain.dto.UserResponseDTO
import com.assignment.payhere.user.web.service.HomeService
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
    fun checkEmail(@RequestBody email: String): UnitResponse<CheckDTO> {
        return UnitResponse(
            data = homeService.checkEmail(email)
        )
    }

    @PostMapping("/register")
    fun register(@Valid @RequestBody dto: RegisterRequestDTO): UnitResponse<UserResponseDTO> {
        return UnitResponse(
            data = homeService.register(dto)
        )
    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody dto: SignInRequestDTO, request: HttpServletRequest): UnitResponse<UserResponseDTO> {
        return UnitResponse(
            data = homeService.signIn(dto, request.session)
        )
    }

    @DeleteMapping("/sign-out")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun signOut(request: HttpServletRequest) {
        if(request.session != null) request.session.invalidate()
    }

    // TODO: 2021/11/25 지우기 
    @GetMapping("/sign-in-test")
    fun signInTest(@SessionData userId: Long): String{
        println(userId)
        println(userId)
        println(userId)
        println(userId)
        println(userId)
        println(userId)
        return "okay"
    }
}