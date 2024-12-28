package org.example.cleanarchitecturelecture.domain.lecture

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.cleanarchitecturelecture.common.exception.ErrorMessage
import org.example.cleanarchitecturelecture.stub.lecture.LectureFixture
import org.example.cleanarchitecturelecture.stub.user.UserFixture
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class LectureScheduleTest {
    @Nested
    @DisplayName("특강 참가 신청")
    inner class Enroll {
        @Test
        @DisplayName("[성공] 특강 참가 신청에 성공한다.")
        fun testEnroll() {
            val lectureSchedule = LectureFixture.createSchedule()
            val user = UserFixture.create(id = 2)

            lectureSchedule.enroll(user)

            assertThat(lectureSchedule.participants)
                .hasSize(1)
            assertThat(lectureSchedule.participants[0].schedule)
                .isEqualTo(lectureSchedule)
            assertThat(lectureSchedule.participants[0].participant)
                .isEqualTo(user)
        }

        @Test
        @DisplayName("[실패] 특강 강사가 참가 신청을 하는 경우 예외가 발생한다.")
        fun testEnrollWithLecturer() {
            val lectureSchedule = LectureFixture.createSchedule()
            val participant = UserFixture.create()

            assertThatThrownBy { lectureSchedule.enroll(participant) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage(ErrorMessage.TEACHER_CANNOT_BE_PARTICIPANT.message)
        }

        @Test
        @DisplayName("[실패] 이미 날짜가 지난 특강에 참가 신청을 하는 경우 예외가 발생한다.")
        fun testEnrollWithPastLecture() {
            val lectureSchedule = LectureFixture.createSchedule(startedAt = LocalDateTime.MIN)
            val participant = UserFixture.create(id = 2)

            assertThatThrownBy { lectureSchedule.enroll(participant) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage(ErrorMessage.LECTURE_SCHEDULE_ALREADY_STARTED.message)
        }

        @Test
        @DisplayName("[실패] 이미 참가 신청한 유저가 다시 참가 신청을 하는 경우 예외가 발생한다.")
        fun testEnrollWithAlreadyEnrolledParticipant() {
            val lectureSchedule = LectureFixture.createSchedule()
            val participant = UserFixture.create(id = 2)

            lectureSchedule.enroll(participant)

            assertThatThrownBy { lectureSchedule.enroll(participant) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage(ErrorMessage.LECTURE_PARTICIPANT_ALREADY_ENROLLED.message)
        }
    }
}
