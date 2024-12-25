package org.example.cleanarchitecturelecture.application.lecture

import org.example.cleanarchitecturelecture.application.lecture.command.EnrollLectureCommand
import org.example.cleanarchitecturelecture.common.exception.ErrorMessage
import org.example.cleanarchitecturelecture.domain.lecture.LectureParticipantCountRepository
import org.example.cleanarchitecturelecture.domain.lecture.LectureScheduleRepository
import org.example.cleanarchitecturelecture.domain.user.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LectureService(
    private val lectureScheduleRepository: LectureScheduleRepository,
    private val lectureParticipantCountRepository: LectureParticipantCountRepository,
) {
    @Transactional
    fun enroll(
        participant: User,
        command: EnrollLectureCommand,
    ) {
        val enrolledSchedule =
            lectureScheduleRepository.findById(command.scheduleId)
                ?: throw IllegalArgumentException(ErrorMessage.LECTURE_SCHEDULE_NOT_FOUND.message)
        require(enrolledSchedule.lecture.id == command.lectureId) { ErrorMessage.LECTURE_SCHEDULE_NOT_BELONG_TO_LECTURE.message }

        val scheduleParticipantCount = lectureParticipantCountRepository.getByScheduleId(enrolledSchedule.id)

        enrolledSchedule.enroll(participant)
        scheduleParticipantCount.increment()
    }
}
