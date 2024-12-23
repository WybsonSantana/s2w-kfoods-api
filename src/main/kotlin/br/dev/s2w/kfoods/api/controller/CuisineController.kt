package br.dev.s2w.kfoods.api.controller

import br.dev.s2w.kfoods.api.domain.model.Cuisine
import br.dev.s2w.kfoods.api.domain.repository.CuisineRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cuisines")
class CuisineController(
    private val cuisineRepository: CuisineRepository
) {

    @GetMapping
    fun list(): List<Cuisine> =
        cuisineRepository.list()

    @GetMapping("/{cuisineId}")
    fun search(@PathVariable cuisineId: Long): Cuisine =
        cuisineRepository.search(cuisineId)
}