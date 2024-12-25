package org.example.cleanarchitecturelecture.api.lecture.request

import org.example.cleanarchitecturelecture.application.lecture.command.LectureDateSearchCommand
import org.springframework.data.domain.Pageable
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalTime

data class LectureDateSearchRequest(
    @field:DateTimeFormat(pattern = "yyyy-MM-dd")
    val date: LocalDate,
    val pageable: Pageable,
) {
    fun toCommand(): LectureDateSearchCommand =
        LectureDateSearchCommand(
            startDate = date.atStartOfDay(),
            endDate = date.atTime(LocalTime.MAX),
            pageable = pageable,
        )
}
