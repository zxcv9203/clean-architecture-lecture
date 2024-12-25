package org.example.cleanarchitecturelecture.application.lecture.command

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.cleanarchitecturelecture.common.exception.ErrorMessage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class EnrollLectureCommandTest {
    @Nested
    @DisplayName("객체 생성")
    inner class Init {
        @ParameterizedTest(name = "userId가 {0}인 경우 예외가 발생한다")
        @ValueSource(longs = [0, -1])
        @DisplayName("userId가 유효하지 않은 경우 예외가 발생한다")
        fun testUserId(userId: Long) {
            assertThatThrownBy { EnrollLectureCommand(userId, 1, 1) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage(ErrorMessage.USER_NOT_FOUND.message)
        }

        @ParameterizedTest(name = "lectureId가 {0}인 경우 예외가 발생한다")
        @ValueSource(longs = [0, -1])
        @DisplayName("lectureId가 유효하지 않은 경우 예외가 발생한다")
        fun testLectureId(lectureId: Long) {
            assertThatThrownBy { EnrollLectureCommand(1, lectureId, 1) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage(ErrorMessage.LECTURE_NOT_FOUND.message)
        }

        @ParameterizedTest(name = "scheduleId가 {0}인 경우 예외가 발생한다")
        @ValueSource(longs = [0, -1])
        @DisplayName("scheduleId가 유효하지 않은 경우 예외가 발생한다")
        fun testScheduleId(scheduleId: Long) {
            assertThatThrownBy { EnrollLectureCommand(1, 1, scheduleId) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage(ErrorMessage.LECTURE_SCHEDULE_NOT_FOUND.message)
        }
    }
}
