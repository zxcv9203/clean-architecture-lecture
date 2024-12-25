package org.example.cleanarchitecturelecture.api.lecture

import org.example.cleanarchitecturelecture.api.lecture.request.LectureDateSearchRequest
import org.example.cleanarchitecturelecture.api.lecture.response.LectureResponses
import org.example.cleanarchitecturelecture.application.lecture.LectureService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/lectures")
class LectureQueryController(
    private val lectureService: LectureService,
) {
    @GetMapping
    fun findAll(request: LectureDateSearchRequest): ResponseEntity<LectureResponses> =
        request
            .toCommand()
            .let { lectureService.findAllByDate(it) }
            .let { ResponseEntity.status(HttpStatus.OK).body(it) }
}
