package org.example.cleanarchitecturelecture.api.lecture.response

import org.springframework.data.domain.Slice
import java.time.LocalDateTime

data class LectureResponses(
    val lectures: List<LectureResponse>,
    val hasNext: Boolean,
)

data class LectureResponse(
    val lectureId: Long,
    val scheduleId: Long,
    val title: String,
    val content: String,
    val teacherName: String,
    val participantCount: Long,
    val startedAt: LocalDateTime,
    val endedAt: LocalDateTime,
)

fun Slice<LectureResponse>.toResponses(): LectureResponses =
    LectureResponses(
        lectures = content,
        hasNext = hasNext(),
    )
