package org.example.cleanarchitecturelecture.stub.user

import org.example.cleanarchitecturelecture.domain.user.User

object UserFixture {
    fun create(
        id: Long = 1,
        name: String = "name",
    ): User =
        User(
            id = id,
            name = name,
        )
}
