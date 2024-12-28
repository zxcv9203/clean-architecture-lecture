package org.example.cleanarchitecturelecture.api.lecture.request

import org.example.cleanarchitecturelecture.common.exception.ErrorMessage

data class EnrollLectureRequest(
    val userId: Long?,
) {
    init {
        require(userId != null) { ErrorMessage.USER_NOT_FOUND.message }
    }
}
