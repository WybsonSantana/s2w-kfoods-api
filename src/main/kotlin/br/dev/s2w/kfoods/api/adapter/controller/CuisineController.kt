package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.adapter.model.CuisinesXmlWrapper
import br.dev.s2w.kfoods.api.domain.model.Cuisine
import br.dev.s2w.kfoods.api.domain.repository.CuisineRepository
import org.springframework.http.MediaType
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
    fun listJson(): List<Cuisine> =
        cuisineRepository.list()

    @GetMapping(produces = [MediaType.APPLICATION_XML_VALUE])
    fun listXml(): CuisinesXmlWrapper =
        CuisinesXmlWrapper(cuisineRepository.list())

    @GetMapping("/{cuisineId}")
    fun search(@PathVariable cuisineId: Long): Cuisine =
        cuisineRepository.search(cuisineId)

}