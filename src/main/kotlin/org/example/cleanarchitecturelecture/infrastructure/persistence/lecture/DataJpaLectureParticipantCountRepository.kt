package org.example.cleanarchitecturelecture.infrastructure.persistence.lecture

import jakarta.persistence.LockModeType
import org.example.cleanarchitecturelecture.domain.lecture.LectureParticipantCount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface DataJpaLectureParticipantCountRepository : JpaRepository<LectureParticipantCount, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(
        """
                SELECT lpc FROM LectureParticipantCount lpc 
                JOIN lpc.schedule s
                WHERE s.id = :scheduleId
        """
    )
    fun findByScheduleId(scheduleId: Long): LectureParticipantCount?
}
