package org.example.cleanarchitecturelecture.domain.lecture

interface LectureParticipantCountRepository {
    fun getByScheduleIdWithLock(scheduleId: Long): LectureParticipantCount
}
