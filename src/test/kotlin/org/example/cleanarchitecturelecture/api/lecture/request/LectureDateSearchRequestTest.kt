package org.example.cleanarchitecturelecture.api.lecture.request

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.springframework.data.domain.Pageable
import java.time.LocalDate
import java.time.LocalTime
import kotlin.test.Test

class LectureDateSearchRequestTest {
    @Nested
    @DisplayName("Command 객체로 변환")
    inner class ToCommand {
        @Test
        @DisplayName("[성공] Command 객체로 변환에 성공한다.")
        fun testToCommandWithDefaultSort() {
            val date = LocalDate.now()
            val pageable = Pageable.ofSize(10)
            val request =
                LectureDateSearchRequest(
                    date = date,
                    pageable = pageable,
                )

            val command = request.toCommand()

            assertThat(command.startDate).isEqualTo(date.atStartOfDay())
            assertThat(command.endDate).isEqualTo(date.atTime(LocalTime.MAX))
            assertThat(command.pageable).isEqualTo(pageable)
        }
    }
}
