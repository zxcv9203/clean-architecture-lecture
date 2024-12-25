package org.example.cleanarchitecturelecture.infrastructure.persistence.lecture

import org.example.cleanarchitecturelecture.domain.lecture.LectureSchedule
import org.example.cleanarchitecturelecture.domain.lecture.LectureScheduleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class JpaLectureScheduleRepository(
    private val dataJpaLectureScheduleRepository: DataJpaLectureScheduleRepository,
) : LectureScheduleRepository {
    override fun findById(id: Long): LectureSchedule? = dataJpaLectureScheduleRepository.findByIdOrNull(id)
}
