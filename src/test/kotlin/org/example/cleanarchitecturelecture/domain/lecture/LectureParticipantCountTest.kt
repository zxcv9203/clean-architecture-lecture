package org.example.cleanarchitecturelecture.domain.lecture

import org.assertj.core.api.Assertions.assertThat
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
    }
}
