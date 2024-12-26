package org.example.cleanarchitecturelecture.infrastructure.persistence.lecture

import org.example.cleanarchitecturelecture.domain.lecture.LectureParticipantCount
import org.example.cleanarchitecturelecture.domain.lecture.LectureParticipantCountRepository
import org.springframework.stereotype.Repository

@Repository
class JpaLectureParticipantCountRepository(
    private val dataJpaLectureParticipantCountRepository: DataJpaLectureParticipantCountRepository,
) : LectureParticipantCountRepository {
    override fun findByScheduleIdWithLock(scheduleId: Long): LectureParticipantCount? =
        dataJpaLectureParticipantCountRepository.findByScheduleId(scheduleId)
}
