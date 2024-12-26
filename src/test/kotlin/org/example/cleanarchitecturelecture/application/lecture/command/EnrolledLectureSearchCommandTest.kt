package org.example.cleanarchitecturelecture.application.lecture.command

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.cleanarchitecturelecture.common.exception.ErrorMessage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.data.domain.Pageable

class EnrolledLectureSearchCommandTest {
    @Nested
    @DisplayName("객체 생성")
    inner class Init {
        @ParameterizedTest(name = "[성공] userId: {0}")
        @ValueSource(longs = [0, -1])
        @DisplayName("[실패] 유효하지 않은 userId가 들어온 경우 예외가 발생한다.")
        fun testInvalidUserId(userId: Long) {
            assertThatThrownBy { EnrolledLectureSearchCommand(userId, Pageable.ofSize(10)) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage(ErrorMessage.USER_NOT_FOUND.message)
        }
    }
}
