package org.example.cleanarchitecturelecture.infrastructure.persistence.lecture

import org.example.cleanarchitecturelecture.domain.lecture.Lecture
import org.example.cleanarchitecturelecture.domain.lecture.LectureRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class JpaLectureRepository(
    private val dataJpaLectureRepository: DataJpaLectureRepository,
) : LectureRepository {
    override fun findById(id: Long): Lecture? = dataJpaLectureRepository.findByIdOrNull(id)
}
