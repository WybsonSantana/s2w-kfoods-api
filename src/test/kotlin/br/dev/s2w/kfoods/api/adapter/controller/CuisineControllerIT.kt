package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.domain.repository.CuisineRepository
import br.dev.s2w.kfoods.api.util.GeneralUtils
import io.restassured.RestAssured.*
import io.restassured.http.ContentType
import org.flywaydb.core.Flyway
import org.hamcrest.Matchers.equalTo
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
internal class CuisineControllerIT : GeneralUtils() {

    @LocalServerPort
    private val serverPort: Int = 0

    @Autowired
    private lateinit var flyway: Flyway

    @Autowired
    private lateinit var cuisineRepository: CuisineRepository

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
    fun `should return correct quantity when querying cuisines`() {
        val correctNumberOfRegisteredCuisines = cuisineRepository.count().toInt()

        given()
            .accept(ContentType.JSON)
            .`when`()
            .get()
            .then()
            .body("", hasSize<Int>(correctNumberOfRegisteredCuisines))
    }

    @Test
    fun `should return status 201 when registering cuisine`() {
        val chineseCuisineInputJson = super.getContentFromResource("/payload/input/cuisine/chinesa.json")

        given()
            .body(chineseCuisineInputJson)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .`when`()
            .post()
            .then()
            .statusCode(HttpStatus.CREATED.value())
    }

    @Test
    fun `should return correct response and status when querying existing cuisine`() {
        val existentCuisineId = 2L
        val americanCuisine = cuisineRepository.findById(existentCuisineId).orElseThrow()

        given()
            .pathParam("cuisineId", existentCuisineId)
            .accept(ContentType.JSON)
            .`when`()["/{cuisineId}"]
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("name", equalTo(americanCuisine.name))
    }

    @Test
    fun `should return status 404 when querying non-existent cuisine`() {
        val nonExistentCuisineId = 100L

        given()
            .pathParam("cuisineId", nonExistentCuisineId)
            .accept(ContentType.JSON)
            .`when`()["/{cuisineId}"]
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
    }

}
