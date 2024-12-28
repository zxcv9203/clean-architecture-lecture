package org.example.cleanarchitecturelecture.application.lecture

import org.assertj.core.api.Assertions.assertThat
import org.example.cleanarchitecturelecture.TestcontainersConfiguration
import org.example.cleanarchitecturelecture.helper.ConcurrentTestHelper
import org.example.cleanarchitecturelecture.stub.lecture.LectureFixture
import org.example.cleanarchitecturelecture.stub.user.UserFixture
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql

@SpringBootTest
@Import(TestcontainersConfiguration::class)
@Sql(scripts = ["classpath:db/setup.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = ["classpath:db/teardown.sql"], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class IntegrationLectureServiceTest {
    @Autowired
    private lateinit var lectureService: LectureService

    @Nested
    @DisplayName("특강 참가 신청")
    inner class Enroll {
        @Test
        @DisplayName("[실패] 동시에 동일한 특강에 대해 40명이 신청했을 때 30명만 성공한다.")
        fun testEnroll() {
            val count = 40
            val requests =
                (1 until count.toLong() + 1)
                    .map { LectureFixture.createEnrollLectureCommand(2, 3, it) }

            val result =
                ConcurrentTestHelper.executeAsyncTasksWithData(count, requests) {
                    val participant = UserFixture.create(id = it.userId)
                    lectureService.enroll(participant, it)
                }

            val successCount = result.count { it }
            val failCount = result.count { !it }

            assertThat(successCount).isEqualTo(30)
            assertThat(failCount).isEqualTo(10)
        }

        @Test
        @DisplayName("[실패] 동일한 유저 정보로 동시에 5번 신청했을 때 1번만 성공한다.")
        fun testEnrollWithSameUser() {
            val count = 5
            val requests =
                (1 until count.toLong() + 1)
                    .map { LectureFixture.createEnrollLectureCommand(2, 3, 1) }

            val result =
                ConcurrentTestHelper.executeAsyncTasksWithData(count, requests) {
                    val participant = UserFixture.create(id = 1)
                    lectureService.enroll(participant, it)
                }

            val successCount = result.count { it }
            val failCount = result.count { !it }

            assertThat(successCount).isEqualTo(1)
            assertThat(failCount).isEqualTo(4)
        }
    }
}
