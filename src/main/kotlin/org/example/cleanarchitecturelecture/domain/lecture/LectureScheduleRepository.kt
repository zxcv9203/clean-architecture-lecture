package org.example.cleanarchitecturelecture.domain.lecture

interface LectureScheduleRepository {
    fun findById(id: Long): LectureSchedule?
}
