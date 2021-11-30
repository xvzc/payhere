package com.assignment.payhere.user.domain.entity

import com.assignment.payhere.user.domain.embeded.Password
import java.io.Serializable
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.persistence.*
import javax.validation.Valid

@Entity
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "email", nullable = false, unique = true, length = 191)
    val email: String = "",

    @Valid
    @Embedded
    var password: Password = Password(),

    @Column(nullable = false)
    val created: ZonedDateTime = ZonedDateTime.now(ZoneId.systemDefault()),
): Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}