package br.dev.s2w.kfoods.api

import br.dev.s2w.kfoods.api.domain.exception.EntityInUseException
import br.dev.s2w.kfoods.api.domain.exception.EntityNotFoundException
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
class CuisineRegisterIT {

    @Autowired
    private lateinit var cuisineRegister: CuisineRegisterService

    @Test
    fun `should assign id when registering kitchen with correct data`() {
        val cuisine = Cuisine(name = "Chinesa")

        cuisineRegister.save(cuisine).also { newCuisine ->
            assertThat(newCuisine.id).isNotNull()
        }
    }

    @Test
    fun `should fail when registering cuisine without name`() {
        val cuisine = Cuisine(name = null)

        assertThrows<ConstraintViolationException> {
            cuisineRegister.save(cuisine)
        }
    }

    @Test
    fun `should fail when deleting cuisine in use`() {
        val cuisineId = 1L

        assertThrows<EntityInUseException> {
            cuisineRegister.remove(cuisineId)
        }
    }

    @Test
    fun `should fail when deleting cuisine not found`() {
        val cuisineId = 100L

        assertThrows<EntityNotFoundException> {
            cuisineRegister.remove(cuisineId)
        }
    }

}
