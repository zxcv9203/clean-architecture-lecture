package org.example.cleanarchitecturelecture.domain.lecture

import jakarta.persistence.*
import org.example.cleanarchitecturelecture.common.model.BaseEntity
import org.example.cleanarchitecturelecture.domain.user.User
import org.hibernate.annotations.Comment

@Entity
@Table(name = "lectures")
class Lecture(
    @Column(name = "title", length = 50, nullable = false)
    @Comment("특강 제목")
    val title: String,
    @Column(name = "content", length = 255, nullable = false)
    @Comment("특강 내용")
    val content: String,
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Comment("특강 강사")
    val user: User,
    @OneToMany(mappedBy = "lecture", fetch = FetchType.LAZY)
    @Comment("특강 일정 목록")
    val schedules: List<LectureSchedule> = mutableListOf(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0,
) : BaseEntity()