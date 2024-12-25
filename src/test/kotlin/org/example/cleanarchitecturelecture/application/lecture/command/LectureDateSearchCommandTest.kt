package org.example.cleanarchitecturelecture.application.lecture.command

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.cleanarchitecturelecture.common.exception.ErrorMessage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

class LectureDateSearchCommandTest {
    @Nested
    @DisplayName("객체 생성")
    inner class Init {
        @Test
        @DisplayName("[실패] 시작일이 종료일보다 늦을 경우 예외가 발생합니다.")
        fun testInitWithEndDateBeforeStartDate() {
            val endDate = LocalDateTime.now()
            val startDate = endDate.plusDays(1)

            assertThatThrownBy {
                LectureDateSearchCommand(
                    startDate = startDate,
                    endDate = endDate,
                    pageable = Pageable.ofSize(10),
                )
            }.isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage(ErrorMessage.LECTURE_END_DATE_BEFORE_START_DATE.message)
        }

        @ParameterizedTest(name = "입력 : {0}")
        @ValueSource(ints = [101])
        @DisplayName("[실패] 페이지 크기가 지정되어 있는 경우 예외가 발생합니다.")
        fun testInitWithSize(size: Int) {
            val startDate = LocalDateTime.now()
            val endDate = startDate.plusDays(1)
            assertThatThrownBy {
                LectureDateSearchCommand(
                    startDate = startDate,
                    endDate = endDate,
                    pageable = Pageable.ofSize(size),
                )
            }.isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage(ErrorMessage.QUERY_SIZE_OUT_OF_RANGE.message)
        }
    }
}
