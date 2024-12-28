package org.example.cleanarchitecturelecture

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {
    companion object {
        @JvmStatic
        val mysqlContainer: MySQLContainer<*> by lazy {
            MySQLContainer(DockerImageName.parse("mysql:8.0.32")).apply {
                withReuse(true)
                start()
            }
        }
    }

    @Bean
    @ServiceConnection
    fun mysqlContainer(): MySQLContainer<*> = mysqlContainer
}
