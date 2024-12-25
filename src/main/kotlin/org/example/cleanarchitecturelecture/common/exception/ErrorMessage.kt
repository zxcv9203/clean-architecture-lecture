package org.example.cleanarchitecturelecture.common.exception

enum class ErrorMessage(
    val message: String,
) {
    USER_NOT_FOUND("존재하지 않는 사용자입니다."),

    LECTURE_NOT_FOUND("존재하지 않는 강의입니다."),
    LECTURE_SCHEDULE_NOT_FOUND("존재하지 않는 강의 일정입니다."),
    LECTURE_SCHEDULE_NOT_BELONG_TO_LECTURE("해당 일정은 해당 강의에 속해있지 않습니다."),
    LECTURE_SCHEDULE_ALREADY_STARTED("이미 시작된 강의 일정입니다."),
    TEACHER_CANNOT_BE_PARTICIPANT("강사는 수강할 수 없습니다."),


    BAD_REQUEST("잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR("서버 내부 오류입니다."),
}
