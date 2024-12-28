package org.example.cleanarchitecturelecture

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class CleanArchitectureLectureApplication

fun main(args: Array<String>) {
    runApplication<CleanArchitectureLectureApplication>(*args)
}
