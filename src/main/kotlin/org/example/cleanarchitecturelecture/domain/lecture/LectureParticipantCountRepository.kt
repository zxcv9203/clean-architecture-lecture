package org.example.cleanarchitecturelecture.domain.lecture

interface LectureParticipantCountRepository {
    fun findByScheduleIdWithLock(scheduleId: Long): LectureParticipantCount?
}
