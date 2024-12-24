package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.domain.exception.EntityInUseException
import br.dev.s2w.kfoods.api.domain.exception.EntityNotFoundException
import br.dev.s2w.kfoods.api.domain.model.Cuisine
import br.dev.s2w.kfoods.api.domain.repository.CuisineRepository
import br.dev.s2w.kfoods.api.domain.service.CuisineRegisterService
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cuisines")
class CuisineController(
    private val cuisineRepository: CuisineRepository,
    private val cuisineRegister: CuisineRegisterService
) {

    @GetMapping
    fun list(): List<Cuisine> =
        cuisineRepository.list()

    @GetMapping("/{cuisineId}")
    fun search(@PathVariable cuisineId: Long): ResponseEntity<Cuisine> {
        val cuisine = cuisineRepository.search(cuisineId)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(cuisine)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun add(@RequestBody cuisine: Cuisine): Cuisine =
        cuisineRegister.save(cuisine)

    @PutMapping("/{cuisineId}")
    fun update(@PathVariable cuisineId: Long, @RequestBody cuisine: Cuisine): ResponseEntity<Cuisine> {
        val currentCuisine = cuisineRepository.search(cuisineId)
            ?: return ResponseEntity.notFound().build()

        BeanUtils.copyProperties(cuisine, currentCuisine, "id")

        return ResponseEntity.ok(cuisineRegister.save(currentCuisine))
    }

    @DeleteMapping("/{cuisineId}")
    fun remove(@PathVariable cuisineId: Long): ResponseEntity<Cuisine> {
        try {
            cuisineRegister.remove(cuisineId)
            return ResponseEntity.noContent().build()
        } catch (e: EntityNotFoundException) {
            return ResponseEntity.notFound().build()
        } catch (e: EntityInUseException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
    }

}