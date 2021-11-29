package com.assignment.payhere.tag.web.controller

import com.assignment.payhere._global.annotaion.SessionData
import com.assignment.payhere._global.dto.ListResponse
import com.assignment.payhere._global.dto.UnitResponse
import com.assignment.payhere.tag.domain.dto.TagAddRequestDTO
import com.assignment.payhere.tag.domain.dto.TagResponseDTO
import com.assignment.payhere.tag.web.service.TagService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tags")
class TagController(
    val tagService: TagService
) {

    @GetMapping("/")
    @ApiOperation(value = "태그 검색", notes = "빈 문자열을 입력시 전체 태그를 조회합니다.")
    fun getTags(
        @SessionData userId: Long,
        @RequestParam(name = "name", required = false, defaultValue = "") name: String
    ): ListResponse<TagResponseDTO?> {
        return ListResponse(data = tagService.getTags(userId, name))
    }

    @PostMapping("/")
    @ApiOperation(value = "태그 추가", notes = "태그를 추가합니다.")
    fun addTag(@SessionData userId: Long, @RequestBody dto: TagAddRequestDTO): UnitResponse<TagResponseDTO?> {
        return UnitResponse(data = tagService.addTag(userId, dto))
    }
}