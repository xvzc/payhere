package com.assignment.payhere.user.domain.embeded

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.Size

@Embeddable
class Password(
    @Size(min = 6, max = 20)
    @Column(name = "password", nullable = false)
    private val value: String = "",
) {
    fun isMatch(rawPassword: String): Boolean {
        return BCryptPasswordEncoder().matches(rawPassword, value)
    }

    companion object {
        fun of(value: String): Password {
            return Password(BCryptPasswordEncoder().encode(value))
        }
    }
}