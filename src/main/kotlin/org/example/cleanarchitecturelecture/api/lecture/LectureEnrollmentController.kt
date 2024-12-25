package org.example.cleanarchitecturelecture.api.lecture

import org.example.cleanarchitecturelecture.api.lecture.request.EnrollLectureRequest
import org.example.cleanarchitecturelecture.application.lecture.LectureEnrollmentUseCase
import org.example.cleanarchitecturelecture.application.lecture.command.EnrollLectureCommand
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/lectures")
class LectureEnrollmentController(
    private val lectureEnrollmentUseCase: LectureEnrollmentUseCase,
) {
    @PostMapping("/{lectureId}/schedules/{scheduleId}/participants")
    fun enroll(
        @PathVariable lectureId: Long,
        @PathVariable scheduleId: Long,
        @RequestBody request: EnrollLectureRequest,
    ): ResponseEntity<Unit> =
        EnrollLectureCommand(
            lectureId = lectureId,
            scheduleId = scheduleId,
            userId = request.userId!!,
        ).let { lectureEnrollmentUseCase.enroll(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }
}
