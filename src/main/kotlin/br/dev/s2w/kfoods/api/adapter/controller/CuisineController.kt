package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.domain.model.Cuisine
import br.dev.s2w.kfoods.api.domain.repository.CuisineRepository
import br.dev.s2w.kfoods.api.domain.service.CuisineRegisterService
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/cuisines")
class CuisineController(
    private val cuisineRepository: CuisineRepository,
    private val cuisineRegister: CuisineRegisterService
) {

    @GetMapping
    fun list(): List<Cuisine> =
        cuisineRepository.findAll()

    @GetMapping("/{cuisineId}")
    fun find(@PathVariable cuisineId: Long): Cuisine =
        cuisineRegister.find(cuisineId)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun add(@RequestBody @Valid cuisine: Cuisine): Cuisine =
        cuisineRegister.save(cuisine)

    @PutMapping("/{cuisineId}")
    fun update(@PathVariable cuisineId: Long, @RequestBody @Valid cuisine: Cuisine): Cuisine =
        cuisineRegister.find(cuisineId).also {
            BeanUtils.copyProperties(cuisine, it, "id")
            cuisineRegister.save(it)
        }

    @DeleteMapping("/{cuisineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remove(@PathVariable cuisineId: Long): Unit =
        cuisineRegister.remove(cuisineId)

}
