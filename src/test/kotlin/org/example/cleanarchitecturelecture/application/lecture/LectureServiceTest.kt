package org.example.cleanarchitecturelecture.application.lecture

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.cleanarchitecturelecture.common.exception.ErrorMessage
import org.example.cleanarchitecturelecture.domain.lecture.LectureParticipantCountRepository
import org.example.cleanarchitecturelecture.domain.lecture.LectureScheduleRepository
import org.example.cleanarchitecturelecture.stub.lecture.LectureFixture
import org.example.cleanarchitecturelecture.stub.user.UserFixture
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class LectureServiceTest {
    @InjectMockKs
    private lateinit var lectureService: LectureService

    @MockK
    private lateinit var lectureScheduleRepository: LectureScheduleRepository

    @MockK
    private lateinit var lectureParticipantCountRepository: LectureParticipantCountRepository

    @Nested
    @DisplayName("특강 참가 신청")
    inner class Enroll {
        @Test
        @DisplayName("[실패] 특강 스케줄이 존재하지 않는 경우 예외가 발생한다.")
        fun testNotExistsLectureSchedule() {
            val participant = UserFixture.create()
            val command = LectureFixture.createEnrollLectureCommand()
            val notExistsScheduleId = command.scheduleId

            every { lectureScheduleRepository.findById(notExistsScheduleId) } returns null

            assertThatThrownBy { lectureService.enroll(participant, command) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage(ErrorMessage.LECTURE_SCHEDULE_NOT_FOUND.message)
        }

        @Test
        @DisplayName("[실패] 특강 스케줄이 해당 특강에 속하지 않은 경우 예외가 발생한다.")
        fun testNotBelongToLecture() {
            val participant = UserFixture.create()
            val command = LectureFixture.createEnrollLectureCommand(lectureId = 99999999)
            val notBelongToLectureScheduleId = command.scheduleId

            every { lectureScheduleRepository.findById(notBelongToLectureScheduleId) } returns LectureFixture.createSchedule()

            assertThatThrownBy { lectureService.enroll(participant, command) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage(ErrorMessage.LECTURE_SCHEDULE_NOT_BELONG_TO_LECTURE.message)
        }
    }
}
