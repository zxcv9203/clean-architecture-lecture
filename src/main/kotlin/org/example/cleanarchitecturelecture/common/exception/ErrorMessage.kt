package org.example.cleanarchitecturelecture.common.exception

enum class ErrorMessage(
    val message: String,
) {
    BAD_REQUEST("잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR("서버 내부 오류입니다."),
}
