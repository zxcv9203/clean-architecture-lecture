package org.example.cleanarchitecturelecture.domain.lecture

import jakarta.persistence.*
import org.example.cleanarchitecturelecture.common.model.BaseEntity
import org.example.cleanarchitecturelecture.domain.user.User
import org.hibernate.annotations.Comment

@Entity
@Table(
    name = "lecture_participants",
    uniqueConstraints = [
        UniqueConstraint(name = "unique_lecture_participant_schedule_user", columnNames = ["user_id", "schedule_id"]),
    ],
)
class LectureParticipant(
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    @Comment("특강 일정")
    val schedule: LectureSchedule,
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Comment("특강 참가자")
    val user: User,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) : BaseEntity()
