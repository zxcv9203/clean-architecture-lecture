package org.example.cleanarchitecturelecture.infrastructure.persistence.user

import org.example.cleanarchitecturelecture.domain.user.User
import org.example.cleanarchitecturelecture.domain.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class JpaUserRepository(
    private val dataJpaUserRepository: DataJpaUserRepository,
) : UserRepository {
    override fun findById(id: Long): User? = dataJpaUserRepository.findByIdOrNull(id)
}
