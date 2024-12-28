package org.example.cleanarchitecturelecture.application.user

import org.example.cleanarchitecturelecture.common.exception.ErrorMessage
import org.example.cleanarchitecturelecture.domain.user.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun getById(userId: Long) = userRepository.findById(userId) ?: throw IllegalArgumentException(ErrorMessage.USER_NOT_FOUND.message)
}
