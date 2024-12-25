package org.example.cleanarchitecturelecture.infrastructure.persistence.lecture

import org.example.cleanarchitecturelecture.domain.lecture.LectureParticipantCount
import org.springframework.data.jpa.repository.JpaRepository

interface DataJpaLectureParticipantCountRepository : JpaRepository<LectureParticipantCount, Long> {
    fun getByScheduleId(scheduleId: Long): LectureParticipantCount
}
