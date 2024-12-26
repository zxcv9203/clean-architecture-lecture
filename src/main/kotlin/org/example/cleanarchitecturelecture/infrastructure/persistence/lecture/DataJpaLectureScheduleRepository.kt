package org.example.cleanarchitecturelecture.infrastructure.persistence.lecture

import org.example.cleanarchitecturelecture.api.lecture.response.LectureResponse
import org.example.cleanarchitecturelecture.domain.lecture.LectureSchedule
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface DataJpaLectureScheduleRepository : JpaRepository<LectureSchedule, Long> {
    @Query(
        """
    SELECT new org.example.cleanarchitecturelecture.api.lecture.response.LectureResponse(
        l.id, s.id, l.title, l.content, t.name, p.count, s.startedAt, s.endedAt
    )
    FROM LectureSchedule s
    JOIN Lecture l ON s.lecture = l
    JOIN l.teacher t
    JOIN LectureParticipantCount p ON s = p.schedule
    WHERE s.startedAt BETWEEN :startDate AND :endDate
""",
    )
    fun findLecturesByDateBetween(
        startDate: LocalDateTime,
        endDate: LocalDateTime,
        pageable: Pageable,
    ): Slice<LectureResponse>

    @Query(
        """
    SELECT new org.example.cleanarchitecturelecture.api.lecture.response.LectureResponse(
        l.id, s.id, l.title, l.content, t.name, pc.count, s.startedAt, s.endedAt
    )
    FROM LectureSchedule s
    JOIN Lecture l ON s.lecture = l
    JOIN l.teacher t
    JOIN LectureParticipant p ON s = p.schedule
    JOIN LectureParticipantCount pc ON s = pc.schedule
    WHERE p.participant.id = :userId
""",
    )
    fun findLecturesByParticipantId(
        userId: Long,
        pageable: Pageable,
    ): Slice<LectureResponse>
}
