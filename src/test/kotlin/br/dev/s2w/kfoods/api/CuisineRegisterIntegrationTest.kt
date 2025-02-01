package br.dev.s2w.kfoods.api

import br.dev.s2w.kfoods.api.domain.model.Cuisine
import br.dev.s2w.kfoods.api.domain.service.CuisineRegisterService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.validation.ConstraintViolationException

@ExtendWith(SpringExtension::class)
@SpringBootTest
class CuisineRegisterIntegrationTest {

    @Autowired
    private lateinit var cuisineRegister: CuisineRegisterService

    @Test
    fun `should assign id when registering kitchen with correct data`() {
        // scenario
        val cuisine = Cuisine(name = "Chinesa")

        // action
        cuisineRegister.save(cuisine).also { newCuisine ->

            // validation
            assertThat(newCuisine.id).isNotNull()
        }
    }

    @Test
    fun `should fail when registering cuisine without name`() {
        // scenario
        val cuisine = Cuisine(name = null)

        // action
        val expectedError = assertThrows<ConstraintViolationException> {
            cuisineRegister.save(cuisine)
        }

        // validation
        assertThat(expectedError).isNotNull()
    }

}
