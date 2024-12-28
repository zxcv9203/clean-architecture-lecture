package org.example.cleanarchitecturelecture.application.lecture.command

import org.example.cleanarchitecturelecture.common.exception.ErrorMessage

data class EnrollLectureCommand(
    val userId: Long,
    val lectureId: Long,
    val scheduleId: Long,
) {
    init {
        require(userId > 0) { ErrorMessage.USER_NOT_FOUND.message }
        require(lectureId > 0) { ErrorMessage.LECTURE_NOT_FOUND.message }
        require(scheduleId > 0) { ErrorMessage.LECTURE_SCHEDULE_NOT_FOUND.message }
    }
}
