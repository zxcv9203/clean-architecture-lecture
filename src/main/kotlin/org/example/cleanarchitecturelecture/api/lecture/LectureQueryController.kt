package org.example.cleanarchitecturelecture.api.lecture

import org.example.cleanarchitecturelecture.api.lecture.request.LectureDateSearchRequest
import org.example.cleanarchitecturelecture.api.lecture.response.LectureResponses
import org.example.cleanarchitecturelecture.application.lecture.LectureQueryUseCase
import org.example.cleanarchitecturelecture.application.lecture.LectureService
import org.example.cleanarchitecturelecture.application.lecture.command.EnrolledLectureSearchCommand
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class LectureQueryController(
    private val lectureQueryUseCase: LectureQueryUseCase,
    private val lectureService: LectureService,
) {
    @GetMapping("/lectures")
    fun findAll(
        request: LectureDateSearchRequest,
        @PageableDefault(size = 10) pageable: Pageable,
    ): ResponseEntity<LectureResponses> =
        request
            .toCommand(pageable)
            .let { lectureService.findAllByDate(it) }
            .let { ResponseEntity.status(HttpStatus.OK).body(it) }

    @GetMapping("/users/{userId}/lectures")
    fun findEnrolledLectures(
        @PathVariable userId: Long,
        @PageableDefault(size = 10) pageable: Pageable,
    ): ResponseEntity<LectureResponses> =
        EnrolledLectureSearchCommand(userId, pageable)
            .let { lectureQueryUseCase.findEnrolledLectures(it) }
            .let { ResponseEntity.status(HttpStatus.OK).body(it) }
}
