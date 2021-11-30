package com.assignment.payhere.user.web.controller

import com.assignment.payhere._global.annotaion.LoginData
import com.assignment.payhere._global.dto.CheckDTO
import com.assignment.payhere._global.dto.UnitResponse
import com.assignment.payhere._global.util.jwt.JwtProvider
import com.assignment.payhere._global.util.jwt.PayhereToken
import com.assignment.payhere.user.domain.dto.RegisterRequestDTO
import com.assignment.payhere.user.domain.dto.SignInRequestDTO
import com.assignment.payhere.user.domain.dto.UserResponseDTO
import com.assignment.payhere.user.web.service.HomeService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid


@RestController
@RequestMapping("")
class HomeController(
    val homeService: HomeService,
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
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun signInToken(@RequestBody dto: SignInRequestDTO, response: HttpServletResponse): String {
        val token = homeService.signIn(dto)
        val cookie = Cookie(JwtProvider.COOKIE_NAME, token.value)
        val duration = Duration.between(LocalDateTime.now(ZoneId.systemDefault()), token.expireDate)

        cookie.maxAge = duration.seconds.toInt()
        cookie.path = "/"

        response.addCookie(cookie)

        return token.value
    }

    @GetMapping("/sign-out")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "로그아웃", notes = "로그아웃을 수행합니다.")
    fun signOut(@LoginData jwt: PayhereToken, response: HttpServletResponse) {
        homeService.signOut(jwt)

        val cookie = Cookie(JwtProvider.COOKIE_NAME, null)
        cookie.maxAge = 0
        cookie.path = "/"

        response.addCookie(cookie)
    }
}