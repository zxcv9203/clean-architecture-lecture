package org.example.cleanarchitecturelecture.domain.lecture

import org.assertj.core.api.Assertions.assertThat
import org.example.cleanarchitecturelecture.stub.lecture.LectureFixture
import org.example.cleanarchitecturelecture.stub.user.UserFixture
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

class LectureTest {
    @Nested
    @DisplayName("특강의 강사가 아닌지 확인")
    inner class IsNotTeacher {
        @Test
        @DisplayName("[성공] 전달받은 유저가 특강의 강사가 아닌 경우 true가 반환된다.")
        fun testIsNotTeacher() {
            val lecture = LectureFixture.create()
            val user = UserFixture.create(id = 99999999)

            val result = lecture.isNotTeacher(user)

            assertThat(result).isTrue()
        }

        @Test
        @DisplayName("[성공] 전달받은 유저가 특강의 강사인 경우 false가 반환된다.")
        fun testIsTeacher() {
            val lecture = LectureFixture.create()
            val user = UserFixture.create()

            val result = lecture.isNotTeacher(user)

            assertThat(result).isFalse()
        }
    }
}
