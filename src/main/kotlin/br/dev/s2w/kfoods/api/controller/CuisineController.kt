package br.dev.s2w.kfoods.api.controller

import br.dev.s2w.kfoods.api.domain.model.Cuisine
import br.dev.s2w.kfoods.api.domain.repository.CuisineRepository
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/cuisines"]) //, produces = [MediaType.APPLICATION_JSON_VALUE])
class CuisineController(
    private val cuisineRepository: CuisineRepository
) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listJson(): List<Cuisine> =
        cuisineRepository.list()

    @GetMapping(produces = [MediaType.APPLICATION_XML_VALUE])
    fun listXml(): List<Cuisine> =
        cuisineRepository.list()
}