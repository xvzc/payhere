package com.assignment.payhere.tag.web.controller

import com.assignment.payhere._global.annotaion.SessionData
import com.assignment.payhere._global.dto.ListResponse
import com.assignment.payhere._global.dto.UnitResponse
import com.assignment.payhere.tag.domain.dto.TagAddRequestDTO
import com.assignment.payhere.tag.domain.dto.TagResponseDTO
import com.assignment.payhere.tag.web.service.TagService
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tags")
class TagController(
    val tagService: TagService
) {

    @GetMapping("/")
    fun getTags(
        @SessionData userId: Long,
        @RequestParam(name = "name", required = false, defaultValue = "") name: String
    ): ListResponse<TagResponseDTO> {
        return ListResponse(data = tagService.getTags(userId, name))
    }

    @PostMapping("/")
    fun addTag(@SessionData userId: Long, @RequestBody dto: TagAddRequestDTO): UnitResponse<TagResponseDTO> {
        return UnitResponse(data = tagService.addTag(userId, dto))
    }
}