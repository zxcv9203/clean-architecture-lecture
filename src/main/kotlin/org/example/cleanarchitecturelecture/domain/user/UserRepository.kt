package org.example.cleanarchitecturelecture.domain.user

interface UserRepository {
    fun findById(id: Long): User?
}