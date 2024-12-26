package org.example.cleanarchitecturelecture.domain.lecture

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.cleanarchitecturelecture.common.exception.ErrorMessage
import org.example.cleanarchitecturelecture.stub.lecture.LectureFixture
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

class LectureParticipantCountTest {
    @Nested
    @DisplayName("특강 참가자 수 증가")
    inner class Increment {
        @Test
        @DisplayName("[성공] 특강 참가자 수가 1 증가한다.")
        fun testIncrement() {
            val lectureParticipantCount = LectureFixture.createParticipantCount()

            lectureParticipantCount.increment()

            assertThat(lectureParticipantCount.count).isEqualTo(1)
        }

        @Test
        @DisplayName("[실패] 최대 참가자 수를 초과하여 참가자 수를 증가하면 예외가 발생한다.")
        fun testIncrementExceedMaxCount() {
            val lectureParticipantCount =
                LectureFixture.createParticipantCount(count = LectureParticipantCount.MAX_COUNT)

            assertThatThrownBy { lectureParticipantCount.increment() }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage(ErrorMessage.LECTURE_PARTICIPANT_COUNT_EXCEEDED.message)
        }
    }
}
