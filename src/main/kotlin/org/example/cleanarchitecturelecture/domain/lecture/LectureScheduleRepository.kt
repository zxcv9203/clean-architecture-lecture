package org.example.cleanarchitecturelecture.domain.lecture

import org.example.cleanarchitecturelecture.api.lecture.response.LectureResponse
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import java.time.LocalDateTime

interface LectureScheduleRepository {
    fun findById(id: Long): LectureSchedule?

    fun findAllByDateBetween(
        startOfDay: LocalDateTime,
        endOfDay: LocalDateTime,
        pageable: Pageable,
    ): Slice<LectureResponse>

    fun findAllByParticipantId(
        userId: Long,
        pageable: Pageable,
    ): Slice<LectureResponse>
}
