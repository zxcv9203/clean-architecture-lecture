package org.example.cleanarchitecturelecture.application.user

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.cleanarchitecturelecture.common.exception.ErrorMessage
import org.example.cleanarchitecturelecture.domain.user.UserRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class UserServiceTest {
    @InjectMockKs
    private lateinit var userService: UserService

    @MockK
    private lateinit var userRepository: UserRepository

    @Nested
    @DisplayName("유저 조회")
    inner class GetUser {
        @Test
        @DisplayName("[실패] 유저가 존재하지 않는 경우 예외가 발생한다.")
        fun testNotFoundUser() {
            val notFoundUserId = 99999999L

            every { userRepository.findById(notFoundUserId) } returns null

            assertThatThrownBy { userService.getById(notFoundUserId) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage(ErrorMessage.USER_NOT_FOUND.message)
        }
    }
}
