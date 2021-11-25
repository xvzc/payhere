package com.assignment.payhere.tag.web.controller

import com.assignment.payhere.tag.web.service.TagService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tags")
class TagController(
    tagService: TagService
) {
}