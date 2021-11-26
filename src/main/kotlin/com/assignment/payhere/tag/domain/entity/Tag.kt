package com.assignment.payhere.tag.domain.entity

import com.assignment.payhere.user.domain.entity.User
import javax.persistence.*

@Entity
class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User = User(),

    @Column(nullable = false, unique = true, length = 191)
    val name: String = "",
)