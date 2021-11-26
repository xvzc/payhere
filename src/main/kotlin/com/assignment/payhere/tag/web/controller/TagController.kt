package com.assignment.payhere.tag.web.controller

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
    companion object {
        const val TAG_PAGE_SIZE = 20
    }

    @GetMapping("/{page}")
    fun getTags(@RequestParam(name = "name") name: String,
                @PathVariable(name = "page") page: Int,
    ): ListResponse<TagResponseDTO> {
        return ListResponse(
            data = tagService.getTags(name, PageRequest.of(page, TAG_PAGE_SIZE))
        )
    }

    @PostMapping("/")
    fun addTag(@RequestBody dto: TagAddRequestDTO): UnitResponse<TagResponseDTO> {
        return UnitResponse(
            data = tagService.addTag(dto)
        )
    }
}