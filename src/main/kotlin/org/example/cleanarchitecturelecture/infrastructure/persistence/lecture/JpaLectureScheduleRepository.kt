package org.example.cleanarchitecturelecture.infrastructure.persistence.lecture

import org.example.cleanarchitecturelecture.api.lecture.response.LectureResponse
import org.example.cleanarchitecturelecture.domain.lecture.LectureSchedule
import org.example.cleanarchitecturelecture.domain.lecture.LectureScheduleRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class JpaLectureScheduleRepository(
    private val dataJpaLectureScheduleRepository: DataJpaLectureScheduleRepository,
) : LectureScheduleRepository {
    override fun findById(id: Long): LectureSchedule? = dataJpaLectureScheduleRepository.findByIdOrNull(id)

    override fun findAllByDateBetween(
        startOfDay: LocalDateTime,
        endOfDay: LocalDateTime,
        pageable: Pageable,
    ): Slice<LectureResponse> = dataJpaLectureScheduleRepository.findLecturesByDateBetween(startOfDay, endOfDay, pageable)

    override fun findAllByParticipantId(
        userId: Long,
        pageable: Pageable,
    ): Slice<LectureResponse> = dataJpaLectureScheduleRepository.findLecturesByParticipantId(userId, pageable)
}
