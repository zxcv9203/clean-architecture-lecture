package org.example.cleanarchitecturelecture.infrastructure.persistence.lecture

import org.assertj.core.api.Assertions.assertThat
import org.example.cleanarchitecturelecture.TestcontainersConfiguration
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.domain.Pageable
import org.springframework.test.context.jdbc.Sql
import java.time.LocalDateTime

@DataJpaTest
@Import(TestcontainersConfiguration::class)
@Sql(scripts = ["classpath:db/setup.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = ["classpath:db/teardown.sql"], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class JpaLectureScheduleRepositoryTest {
    @Autowired
    private lateinit var jpaLectureScheduleRepository: DataJpaLectureScheduleRepository

    @Nested
    @DisplayName("해당하는 날짜의 특강 목록 조회")
    inner class FindAllByDateBetween {
        @Test
        @DisplayName("[성공] 해당하는 날짜의 특강 목록을 조회한다.")
        fun findAllByDateBetween() {
            val result =
                jpaLectureScheduleRepository.findLecturesByDateBetween(
                    LocalDateTime.of(2024, 12, 31, 0, 0),
                    LocalDateTime.of(2024, 12, 31, 23, 59),
                    Pageable.ofSize(10),
                )

            assertThat(result).hasSize(10)
            assertThat(result.hasNext()).isTrue()
        }

        @Test
        @DisplayName("[성공] 모든 데이터가 조회된 경우 hasNext는 false이다.")
        fun findAllByDateBetweenWithLastPage() {
            val result =
                jpaLectureScheduleRepository.findLecturesByDateBetween(
                    LocalDateTime.of(2024, 12, 31, 0, 0),
                    LocalDateTime.of(2024, 12, 31, 23, 59),
                    Pageable.ofSize(100),
                )

            assertThat(result).hasSize(11)
            assertThat(result.hasNext()).isFalse()
        }
    }
}
