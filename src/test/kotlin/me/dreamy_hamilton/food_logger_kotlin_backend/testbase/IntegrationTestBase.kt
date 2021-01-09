package me.dreamy_hamilton.food_logger_kotlin_backend.testbase

import javax.annotation.PostConstruct
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
abstract class IntegrationTestBase {
    @LocalServerPort
    private lateinit var port: String

    protected lateinit var testClient: TestClient

    @PostConstruct
    fun setup() {
        testClient = TestClient(port)
    }
}