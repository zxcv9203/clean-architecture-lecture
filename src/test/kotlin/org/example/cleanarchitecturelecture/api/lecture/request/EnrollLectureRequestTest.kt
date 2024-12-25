package org.example.cleanarchitecturelecture.api.lecture.request

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.cleanarchitecturelecture.common.exception.ErrorMessage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class EnrollLectureRequestTest {
    @Nested
    @DisplayName("객체 생성")
    inner class Init {
        @Test
        @DisplayName("[실패] 값이 들어오지 않은 경우 예외가 발생한다.")
        fun testNull() {
            val noInputUserId = null

            assertThatThrownBy { EnrollLectureRequest(noInputUserId) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage(ErrorMessage.USER_NOT_FOUND.message)
        }
    }
}
