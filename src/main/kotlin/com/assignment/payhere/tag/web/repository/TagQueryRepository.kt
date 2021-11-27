package com.assignment.payhere.tag.web.repository

import com.assignment.payhere.tag.domain.entity.QTag
import com.assignment.payhere.tag.domain.entity.Tag
import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class TagQueryRepository(
        val queryFactory: JPAQueryFactory
) {

    fun findByUserIdAndNameContains(userId: Long, name: String): List<Tag>{
        val tag = QTag.tag

        return queryFactory
                .select(tag)
                .from(tag)
                .where(
                    tag.user.id.eq(userId)
                        .and(tag.name.contains(name))
                )
                .fetch()
    }

}