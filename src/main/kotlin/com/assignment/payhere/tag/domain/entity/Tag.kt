package com.assignment.payhere.tag.domain.entity

import com.assignment.payhere.user.domain.entity.User
import java.io.Serializable
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
): Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tag

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun isOwnedBy(user: User): Boolean {
        return this.user == user
    }
}