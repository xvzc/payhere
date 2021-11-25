package com.assignment.payhere.user.web.controller

import com.assignment.payhere._global.dto.CheckResponse
import com.assignment.payhere.user.domain.dto.SignInRequestDTO
import com.assignment.payhere.user.domain.dto.RegisterRequestDTO
import com.assignment.payhere.user.domain.dto.UserResponseDTO
import com.assignment.payhere.user.web.service.HomeService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("")
class HomeController(
    val homeService: HomeService
) {
    @PostMapping("/check-email")
    fun checkEmail(@RequestBody email: String): CheckResponse {
        return CheckResponse(
            success = homeService.checkEmail(email)
        )
    }

    @PostMapping("/register")
    fun resiter(@RequestBody dto: RegisterRequestDTO): CheckResponse {
        return CheckResponse(
            success = homeService.register(dto)
        )
    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody dto: SignInRequestDTO, request: HttpServletRequest): UserResponseDTO {
        return homeService.signIn(dto, request.session)
    }

    @GetMapping("/sign-out")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun signOut(request: HttpServletRequest) {
        request.session.invalidate()
    }

    @GetMapping("/sign-in-test")
    fun signInTest(request: HttpServletRequest): String{
        return if(request.session.getAttribute("id") == null)
            "failed"
        else
            "okay"
    }
}