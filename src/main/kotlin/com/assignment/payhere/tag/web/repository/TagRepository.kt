package com.assignment.payhere.tag.web.repository

import com.assignment.payhere.tag.domain.entity.Tag
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TagRepository: JpaRepository<Tag, Long> {
    fun findByNameContains(name:String, pageable: Pageable): List<Tag>
}