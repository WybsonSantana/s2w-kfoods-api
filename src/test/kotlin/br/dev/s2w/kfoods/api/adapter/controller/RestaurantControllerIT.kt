package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.domain.repository.RestaurantRepository
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
internal class RestaurantControllerIT : GeneralUtils() {

    @LocalServerPort
    private val serverPort: Int = 0

    @Autowired
    private lateinit var flyway: Flyway

    @Autowired
    private lateinit var restaurantRepository: RestaurantRepository

    companion object {
        private const val RESTAURANT_ID_PARAM: String = "restaurantId"
        private const val RESTAURANT_ID_MAPPING: String = "/{restaurantId}"
        private const val NAME_ATTRIBUTE: String = "name"
        private const val EXISTENT_AND_IN_USE_BURGER_TOP_RESTAURANT_ID: Long = 2L
        private const val NON_EXISTENT_RESTAURANT_ID: Long = 100L
    }

    @BeforeEach
    fun setup() {
        enableLoggingOfRequestAndResponseIfValidationFails()
        port = serverPort
        basePath = "/restaurants"

        flyway.migrate()
    }

    @Test
    fun `should return status 200 when querying restaurants`() {
        given()
            .accept(ContentType.JSON)
            .`when`()
            .get()
            .then()
            .statusCode(HttpStatus.OK.value())
    }

    @Test
    fun `should return correct quantity when querying restaurants`() {
        val correctNumberOfRegisteredRestaurants = restaurantRepository.count().toInt()

        given()
            .accept(ContentType.JSON)
            .`when`()
            .get()
            .then()
            .body("", hasSize<Int>(correctNumberOfRegisteredRestaurants))
    }

    @Test
    fun `should return correct response and status when querying existing restaurant`() {
        val burgerTopRestaurant = restaurantRepository
            .findById(EXISTENT_AND_IN_USE_BURGER_TOP_RESTAURANT_ID).orElseThrow()

        given()
            .pathParam(RESTAURANT_ID_PARAM, EXISTENT_AND_IN_USE_BURGER_TOP_RESTAURANT_ID)
            .accept(ContentType.JSON)
            .`when`()
            .get(RESTAURANT_ID_MAPPING)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(NAME_ATTRIBUTE, equalTo<String>(burgerTopRestaurant.name))
    }

    @Test
    fun `should return status 201 when registering restaurant`() {
        val newYorkBarbecueRestaurantInputJson = super
            .getContentFromResource("/payload/input/restaurant/new-york-barbecue.json")

        given()
            .body(newYorkBarbecueRestaurantInputJson)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .`when`()
            .post()
            .then()
            .statusCode(HttpStatus.CREATED.value())
    }

    @Test
    fun `should return status 200 when updating restaurant`() {
        val burgerTopRestaurantWithDeliveryFeeUpdateInputJson = super
            .getContentFromResource("/payload/input/restaurant/burger-top-with-delivery-fee-update.json")

        given()
            .pathParam("restaurantId", EXISTENT_AND_IN_USE_BURGER_TOP_RESTAURANT_ID)
            .body(burgerTopRestaurantWithDeliveryFeeUpdateInputJson)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .`when`()
            .put(RESTAURANT_ID_MAPPING)
            .then()
            .statusCode(HttpStatus.OK.value())
    }

    @Test
    fun `should return status 404 when querying non-existent restaurant`() {
        given()
            .pathParam(RESTAURANT_ID_PARAM, NON_EXISTENT_RESTAURANT_ID)
            .accept(ContentType.JSON)
            .`when`()
            .get(RESTAURANT_ID_MAPPING)
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
    }

    @Test
    fun `should return status 404 when updating non-existent restaurant`() {
        val burgerTopRestaurantWithDeliveryFeeUpdateInputJson = super
            .getContentFromResource("/payload/input/restaurant/burger-top-with-delivery-fee-update.json")

        given()
            .pathParam("restaurantId", NON_EXISTENT_RESTAURANT_ID)
            .body(burgerTopRestaurantWithDeliveryFeeUpdateInputJson)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .`when`()
            .put(RESTAURANT_ID_MAPPING)
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
    }

    @Test
    fun `should return status 400 when registering restaurant without name`() {
        val newYorkBarbecueRestaurantWithoutNameInputJson = super
            .getContentFromResource("/payload/input/restaurant/new-york-barbecue-without-name.json")

        given()
            .body(newYorkBarbecueRestaurantWithoutNameInputJson)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .`when`()
            .post()
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
    }

    @Test
    fun `should return status 400 when registering restaurant without delivery fee`() {
        val newYorkBarbecueRestaurantWithoutDeliveryFeeInputJson = super
            .getContentFromResource("/payload/input/restaurant/new-york-barbecue-without-delivery-fee.json")

        given()
            .body(newYorkBarbecueRestaurantWithoutDeliveryFeeInputJson)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .`when`()
            .post()
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
    }

    @Test
    fun `should return status 400 when registering restaurant with delivery fee less than zero`() {
        val newYorkBarbecueRestaurantWithDeliveryFeeLessThanZeroInputJson = super
            .getContentFromResource("/payload/input/restaurant/new-york-barbecue-with-delivery-fee-less-than-zero.json")

        given()
            .body(newYorkBarbecueRestaurantWithDeliveryFeeLessThanZeroInputJson)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .`when`()
            .post()
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
    }

    @Test
    fun `should return status 400 when registering restaurant without cuisine`() {
        val newYorkBarbecueRestaurantWithoutCuisineInputJson = super
            .getContentFromResource("/payload/input/restaurant/new-york-barbecue-without-cuisine.json")

        given()
            .body(newYorkBarbecueRestaurantWithoutCuisineInputJson)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .`when`()
            .post()
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
    }

    @Test
    fun `should return status 400 when registering restaurant without cuisine id`() {
        val newYorkBarbecueRestaurantWithoutCuisineIdInputJson = super
            .getContentFromResource("/payload/input/restaurant/new-york-barbecue-without-cuisine-id.json")

        given()
            .body(newYorkBarbecueRestaurantWithoutCuisineIdInputJson)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .`when`()
            .post()
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
    }

    @Test
    fun `should return status 400 when registering restaurant with non-existent cuisine`() {
        val newYorkBarbecueRestaurantWithNonExistentCuisineInputJson = super
            .getContentFromResource("/payload/input/restaurant/new-york-barbecue-with-non-existent-cuisine.json")

        given()
            .body(newYorkBarbecueRestaurantWithNonExistentCuisineInputJson)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .`when`()
            .post()
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
    }

}
