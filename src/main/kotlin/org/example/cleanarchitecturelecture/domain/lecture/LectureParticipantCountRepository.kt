package org.example.cleanarchitecturelecture.domain.lecture

interface LectureParticipantCountRepository {
    fun getByScheduleId(scheduleId: Long): LectureParticipantCount
}
