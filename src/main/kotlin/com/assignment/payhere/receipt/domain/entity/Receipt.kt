package com.assignment.payhere.receipt.domain.entity

import javax.persistence.*
import com.assignment.payhere.tag.domain.entity.Tag
import com.assignment.payhere.user.domain.entity.User
import java.io.Serializable
import java.time.ZoneId
import java.time.ZonedDateTime

@Entity
class Receipt(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User = User(),

    @Column(nullable = false, unique = true, length = 191)
    var amount: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    var tag: Tag = Tag(),

    @Column(nullable = false, unique = true, length = 191)
    var description: String = "",

    @Column(nullable = false)
    val created: ZonedDateTime = ZonedDateTime.now(ZoneId.systemDefault()),

    @Column(nullable = false)
    var deleted: Char = 'N'
): Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Receipt

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun isOwnedBy(user: User): Boolean {
        return user == this.user
    }
}