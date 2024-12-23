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
    private val id: Long = 0,
) : BaseEntity()
