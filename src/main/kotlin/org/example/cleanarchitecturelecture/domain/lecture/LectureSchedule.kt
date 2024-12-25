package org.example.cleanarchitecturelecture.domain.lecture

import jakarta.persistence.*
import org.example.cleanarchitecturelecture.common.exception.ErrorMessage
import org.example.cleanarchitecturelecture.common.model.BaseEntity
import org.example.cleanarchitecturelecture.domain.user.User
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "lecture_schedules")
class LectureSchedule(
    @Column(name = "started_at", nullable = false)
    @Comment("특강 시작 시간")
    val startedAt: LocalDateTime,
    @Column(name = "ended_at", nullable = false)
    @Comment("특강 종료 시간")
    val endedAt: LocalDateTime,
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    @Comment("특강")
    val lecture: Lecture,
    @OneToMany(mappedBy = "schedule", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @Comment("특강 참가자 목록")
    val participants: MutableList<LectureParticipant> = mutableListOf(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) : BaseEntity() {
    fun enroll(participant: User) {
        require(lecture.isNotTeacher(participant)) { ErrorMessage.TEACHER_CANNOT_BE_PARTICIPANT.message }
        require(startedAt.isAfter(LocalDateTime.now())) { ErrorMessage.LECTURE_SCHEDULE_ALREADY_STARTED.message }

        participants.add(LectureParticipant(this, participant))
    }
}
