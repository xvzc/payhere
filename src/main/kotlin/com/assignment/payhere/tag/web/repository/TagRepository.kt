package com.assignment.payhere.tag.web.repository

import com.assignment.payhere.tag.domain.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository: JpaRepository<Tag, Long> {
}