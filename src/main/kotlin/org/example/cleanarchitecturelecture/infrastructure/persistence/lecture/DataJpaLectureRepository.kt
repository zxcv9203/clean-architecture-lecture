package org.example.cleanarchitecturelecture.infrastructure.persistence.lecture

import org.example.cleanarchitecturelecture.domain.lecture.Lecture
import org.springframework.data.jpa.repository.JpaRepository

interface DataJpaLectureRepository : JpaRepository<Lecture, Long>
