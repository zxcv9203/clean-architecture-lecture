package org.example.cleanarchitecturelecture.application.lecture

import org.example.cleanarchitecturelecture.api.lecture.response.LectureResponses
import org.example.cleanarchitecturelecture.application.lecture.command.EnrolledLectureSearchCommand
import org.example.cleanarchitecturelecture.application.user.UserService
import org.springframework.stereotype.Component

@Component
class LectureQueryUseCase(
    private val userService: UserService,
    private val lectureService: LectureService,
) {
    fun findEnrolledLectures(command: EnrolledLectureSearchCommand): LectureResponses {
        userService.getById(command.userId)
        return lectureService.findEnrolledLectures(command)
    }
}
