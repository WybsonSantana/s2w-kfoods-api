package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.domain.model.Cuisine
import br.dev.s2w.kfoods.api.domain.repository.CuisineRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController(
    private val cuisineRepository: CuisineRepository
) {

    @GetMapping("/cuisines/by-name")
    fun findCuisinesByName(@RequestParam("name") name: String): List<Cuisine> =
        cuisineRepository.findByName(name)

}
