package org.example.cleanarchitecturelecture.domain.user

import jakarta.persistence.*
import org.example.cleanarchitecturelecture.common.model.BaseEntity
import org.hibernate.annotations.Comment

@Entity
@Table(name = "users")
class User(
    @Column(name = "name", length = 20, nullable = false)
    @Comment("사용자 이름")
    val name: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) : BaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is User) return false

        return id != 0L && id == other.id
    }

    override fun hashCode(): Int = if (id == 0L) System.identityHashCode(this) else id.hashCode()
}
