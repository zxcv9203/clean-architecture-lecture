package org.example.cleanarchitecturelecture.application.lecture

import org.example.cleanarchitecturelecture.application.lecture.command.EnrollLectureCommand
import org.example.cleanarchitecturelecture.application.user.UserService
import org.springframework.stereotype.Component

@Component
class LectureEnrollmentUseCase(
    private val userService: UserService,
    private val lectureService: LectureService,
) {
    fun enroll(command: EnrollLectureCommand) {
        val participant = userService.getById(command.userId)
        lectureService.enroll(participant, command)
    }
}
