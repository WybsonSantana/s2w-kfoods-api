package br.dev.s2w.kfoods.api

import io.restassured.RestAssured.*
import io.restassured.http.ContentType
import org.flywaydb.core.Flyway
import org.hamcrest.Matchers.hasItems
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CuisineRegisterIT {

    @LocalServerPort
    private val serverPort: Int = 0

    @Autowired
    private lateinit var flyway: Flyway

    @BeforeEach
    fun setup() {
        enableLoggingOfRequestAndResponseIfValidationFails()
        port = serverPort
        basePath = "/cuisines"

        flyway.migrate()
    }

    @Test
    fun `should return status 200 when querying cuisines`() {
        given()
            .accept(ContentType.JSON)
            .`when`()
            .get()
            .then()
            .statusCode(HttpStatus.OK.value())
    }

    @Test
    fun `should contain 2 cuisines when querying cuisines`() {
        given()
            .accept(ContentType.JSON)
            .`when`()
            .get()
            .then()
            .body("", hasSize<Int>(2))
            .body("name", hasItems("Tailandesa", "Indiana"))
    }

    @Test
    fun `should return status 201 when registering cuisine`() {
        given()
            .body("{\"name\":\"Chinesa\"}")
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .`when`()
            .post()
            .then()
            .statusCode(HttpStatus.CREATED.value())
    }

}
