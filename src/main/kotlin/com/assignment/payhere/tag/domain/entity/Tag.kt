package com.assignment.payhere.tag.domain.entity

import javax.persistence.*

@Entity
class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true, length = 191)
    val name: String = "",
)