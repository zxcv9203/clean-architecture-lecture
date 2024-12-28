package org.example.cleanarchitecturelecture.application.lecture.command

import org.example.cleanarchitecturelecture.common.exception.ErrorMessage
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

data class LectureDateSearchCommand(
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val pageable: Pageable,
) {
    init {
        require(startDate < endDate) { ErrorMessage.LECTURE_END_DATE_BEFORE_START_DATE.message }
        require(pageable.pageSize in 1..100) { ErrorMessage.QUERY_SIZE_OUT_OF_RANGE.message }
    }
}
