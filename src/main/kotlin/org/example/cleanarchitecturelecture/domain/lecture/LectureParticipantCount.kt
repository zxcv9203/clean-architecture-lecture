package org.example.cleanarchitecturelecture.domain.lecture

import jakarta.persistence.*
import org.example.cleanarchitecturelecture.common.model.BaseEntity
import org.hibernate.annotations.Comment

@Entity
@Table(name = "lecture_participant_counts")
class LectureParticipantCount(
    @OneToOne
    @JoinColumn(name = "schedule_id")
    @Comment("특강 일정")
    val schedule: LectureSchedule,
    @Column(name = "count", nullable = false)
    @Comment("특강 참가자 수")
    var count: Int = 0,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) : BaseEntity() {
    fun increment() {
        count++
    }
}
