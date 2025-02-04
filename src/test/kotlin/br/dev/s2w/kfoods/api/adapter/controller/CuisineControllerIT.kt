package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.domain.model.Cuisine
import br.dev.s2w.kfoods.api.domain.repository.CuisineRepository
import br.dev.s2w.kfoods.api.domain.service.CuisineRegisterService
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

    @Autowired
    private lateinit var cuisineRegister: CuisineRegisterService

    companion object {
        private const val CUISINE_ID_PARAM: String = "cuisineId"
        private const val CUISINE_ID_MAPPING: String = "/{cuisineId}"
        private const val NAME_ATTRIBUTE: String = "name"
        private const val EXISTENT_AND_IN_USE_AMERICAN_CUISINE_ID: Long = 2L
        private const val NON_EXISTENT_CUISINE_ID: Long = 100L
    }

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
    fun `should return correct response and status when querying existing cuisine`() {
        val americanCuisine = cuisineRepository
            .findById(EXISTENT_AND_IN_USE_AMERICAN_CUISINE_ID).orElseThrow()

        given()
            .pathParam(CUISINE_ID_PARAM, EXISTENT_AND_IN_USE_AMERICAN_CUISINE_ID)
            .accept(ContentType.JSON)
            .`when`()
            .get(CUISINE_ID_MAPPING)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(NAME_ATTRIBUTE, equalTo(americanCuisine.name))
    }

    @Test
    fun `should return status 201 when registering cuisine`() {
        val chineseCuisineInputJson = super
            .getContentFromResource("/payload/input/cuisine/chinese.json")

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
    fun `should return status 200 when updating cuisine`() {
        val northAmericanCuisineInputJson = super
            .getContentFromResource("/payload/input/cuisine/north-american.json")

        given()
            .pathParam(CUISINE_ID_PARAM, EXISTENT_AND_IN_USE_AMERICAN_CUISINE_ID)
            .body(northAmericanCuisineInputJson)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .`when`()
            .put(CUISINE_ID_MAPPING)
            .then()
            .statusCode(HttpStatus.OK.value())
    }

    @Test
    fun `should return status 204 when deleting cuisine`() {
        val northAmericanCuisine = cuisineRegister.save(Cuisine(name = "Norte Americana"))

        given()
            .pathParam(CUISINE_ID_PARAM, northAmericanCuisine.id)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .`when`()
            .delete(CUISINE_ID_MAPPING)
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
    }

    @Test
    fun `should return status 404 when querying non-existent cuisine`() {
        given()
            .pathParam(CUISINE_ID_PARAM, NON_EXISTENT_CUISINE_ID)
            .accept(ContentType.JSON)
            .`when`()
            .get(CUISINE_ID_MAPPING)
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
    }

    @Test
    fun `should return status 404 when deleting non-existent cuisine`() {
        given()
            .pathParam(CUISINE_ID_PARAM, NON_EXISTENT_CUISINE_ID)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .`when`()
            .delete(CUISINE_ID_MAPPING)
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
    }

    @Test
    fun `should return status 404 when updating non-existent cuisine`() {
        val northAmericanCuisineInputJson = super
            .getContentFromResource("/payload/input/cuisine/north-american.json")

        given()
            .pathParam(CUISINE_ID_PARAM, NON_EXISTENT_CUISINE_ID)
            .body(northAmericanCuisineInputJson)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .`when`()
            .put(CUISINE_ID_MAPPING)
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
    }

    @Test
    fun `should return status 400 when registering cuisine without name`() {
        val cuisineWithoutNameInputJson = super
            .getContentFromResource("/payload/input/cuisine/cuisine-without-name.json")

        given()
            .body(cuisineWithoutNameInputJson)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .`when`()
            .post()
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
    }

    @Test
    fun `should return status 409 when deleting cuisine in use`() {
        given()
            .pathParam(CUISINE_ID_PARAM, EXISTENT_AND_IN_USE_AMERICAN_CUISINE_ID)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .`when`()
            .delete(CUISINE_ID_MAPPING)
            .then()
            .statusCode(HttpStatus.CONFLICT.value())
    }

}
