package com.assignment.payhere.user.domain.embeded

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Password(
    @Column(name = "password", nullable = false, length = 512)
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