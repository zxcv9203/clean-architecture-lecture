package org.example.cleanarchitecturelecture

import org.springframework.boot.fromApplication
import org.springframework.boot.with

// 테스트 환경으로 애플리케이션 실행 (테스트 컨테이너 이용해서 애플리케이션 실행)
fun main(args: Array<String>) {
    fromApplication<CleanArchitectureLectureApplication>().with(TestcontainersConfiguration::class).run(*args)
}
