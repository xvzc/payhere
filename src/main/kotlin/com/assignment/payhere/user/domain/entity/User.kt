package com.assignment.payhere.user.domain.entity

import com.assignment.payhere.user.domain.embeded.Password
import lombok.EqualsAndHashCode
import java.io.Serializable
import java.time.OffsetDateTime
import java.time.ZoneOffset
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
    val created: OffsetDateTime = OffsetDateTime.now(ZoneOffset.of("+09:00")),
): Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}