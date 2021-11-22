package com.assignment.payhere.user.domain.entity

import lombok.EqualsAndHashCode
import java.io.Serializable
import java.time.OffsetDateTime
import java.time.ZoneOffset
import javax.persistence.*

@Entity
@EqualsAndHashCode
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true, length = 191)
    val email: String = "",

    @Column(nullable = false, length = 512)
    var password: String = "",

    @Column(nullable = false)
    val created: OffsetDateTime = OffsetDateTime.now(ZoneOffset.of("+00:00")),
): Serializable