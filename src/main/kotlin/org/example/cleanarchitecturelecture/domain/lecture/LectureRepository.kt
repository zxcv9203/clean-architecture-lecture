package org.example.cleanarchitecturelecture.domain.lecture

interface LectureRepository {
    fun findById(id: Long): Lecture?
}
