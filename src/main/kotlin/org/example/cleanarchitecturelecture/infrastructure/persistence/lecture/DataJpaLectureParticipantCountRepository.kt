package org.example.cleanarchitecturelecture.infrastructure.persistence.lecture

import jakarta.persistence.LockModeType
import org.example.cleanarchitecturelecture.domain.lecture.LectureParticipantCount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock

interface DataJpaLectureParticipantCountRepository : JpaRepository<LectureParticipantCount, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findByScheduleId(scheduleId: Long): LectureParticipantCount?
}
