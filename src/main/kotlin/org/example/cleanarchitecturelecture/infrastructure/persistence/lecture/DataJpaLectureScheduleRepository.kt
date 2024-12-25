package org.example.cleanarchitecturelecture.infrastructure.persistence.lecture

import org.example.cleanarchitecturelecture.domain.lecture.LectureSchedule
import org.springframework.data.jpa.repository.JpaRepository

interface DataJpaLectureScheduleRepository : JpaRepository<LectureSchedule, Long>
