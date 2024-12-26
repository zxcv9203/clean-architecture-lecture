package org.example.cleanarchitecturelecture.stub.lecture

import org.example.cleanarchitecturelecture.application.lecture.command.EnrollLectureCommand
import org.example.cleanarchitecturelecture.domain.lecture.Lecture
import org.example.cleanarchitecturelecture.domain.lecture.LectureParticipant
import org.example.cleanarchitecturelecture.domain.lecture.LectureParticipantCount
import org.example.cleanarchitecturelecture.domain.lecture.LectureSchedule
import org.example.cleanarchitecturelecture.domain.user.User
import org.example.cleanarchitecturelecture.stub.user.UserFixture
import java.time.LocalDateTime

object LectureFixture {
    fun createEnrollLectureCommand(
        lectureId: Long = 1L,
        scheduleId: Long = 1L,
        userId: Long = 1L,
    ): EnrollLectureCommand =
        EnrollLectureCommand(
            lectureId = lectureId,
            scheduleId = scheduleId,
            userId = userId,
        )

    fun createSchedule(
        id: Long = 1L,
        startedAt: LocalDateTime = LocalDateTime.of(2025, 1, 1, 10, 0),
        endedAt: LocalDateTime = LocalDateTime.of(2025, 1, 1, 11, 0),
        lecture: Lecture = create(),
    ): LectureSchedule =
        LectureSchedule(
            id = id,
            startedAt = startedAt,
            endedAt = endedAt,
            lecture = lecture,
        )

    fun create(
        id: Long = 1L,
        title: String = "title",
        content: String = "content",
        userId: Long = 1L,
        userName: String = "name",
    ): Lecture =
        Lecture(
            id = id,
            title = title,
            content = content,
            teacher = UserFixture.create(userId, userName),
        )

    fun createParticipantCount(
        id: Long = 1L,
        count: Int = 0,
    ): LectureParticipantCount =
        LectureParticipantCount(
            id = id,
            schedule = createSchedule(),
            count = count,
        )

    fun createParticipant(
        id: Long = 1L,
        schedule: LectureSchedule = createSchedule(),
        participant: User = UserFixture.create(),
    ): LectureParticipant =
        LectureParticipant(
            id = id,
            schedule = schedule,
            participant = participant,
        )
}
