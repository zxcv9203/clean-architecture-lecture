package org.example.cleanarchitecturelecture.domain.lecture

import jakarta.persistence.*
import org.example.cleanarchitecturelecture.common.model.BaseEntity
import org.hibernate.annotations.Comment
import java.time.LocalDate

@Entity
@Table(name = "lecture_schedules")
class LectureSchedule(
    @Column(name = "started_at", nullable = false)
    @Comment("특강 시작 시간")
    val startedAt: LocalDate,
    @Column(name = "ended_at", nullable = false)
    @Comment("특강 종료 시간")
    val endedAt: LocalDate,
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    @Comment("특강")
    val lecture: Lecture,
    @OneToOne(mappedBy = "schedule", cascade = [CascadeType.ALL])
    @Comment("특강 참가자 수")
    var participantCount: LectureParticipantCount,
    @OneToMany(mappedBy = "schedule", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @Comment("특강 참가자 목록")
    val participants: List<LectureParticipant> = mutableListOf(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) : BaseEntity()
