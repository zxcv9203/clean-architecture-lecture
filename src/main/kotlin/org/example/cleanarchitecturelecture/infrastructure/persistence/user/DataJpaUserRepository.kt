package org.example.cleanarchitecturelecture.infrastructure.persistence.user

import org.example.cleanarchitecturelecture.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface DataJpaUserRepository : JpaRepository<User, Long>
