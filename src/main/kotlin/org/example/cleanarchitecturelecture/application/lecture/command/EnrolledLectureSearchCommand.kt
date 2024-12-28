package org.example.cleanarchitecturelecture.application.lecture.command

import org.example.cleanarchitecturelecture.common.exception.ErrorMessage
import org.springframework.data.domain.Pageable

data class EnrolledLectureSearchCommand(
    val userId: Long,
    val pageable: Pageable,
) {
    init {
        require(userId > 0) { ErrorMessage.USER_NOT_FOUND.message }
    }
}
