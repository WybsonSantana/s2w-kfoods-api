package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.domain.model.City
import br.dev.s2w.kfoods.api.domain.repository.CityRepository
import br.dev.s2w.kfoods.api.domain.service.CityRegisterService
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cities")
class CityController(
    private val cityRepository: CityRepository,
    private val cityRegister: CityRegisterService
) {

    @GetMapping
    fun list(): List<City> =
        cityRepository.findAll()

    @GetMapping("/{cityId}")
    fun find(@PathVariable cityId: Long): City =
        cityRegister.find(cityId)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun add(@RequestBody city: City) =
        cityRegister.save(city)

    @PutMapping("/{cityId}")
    fun update(@PathVariable cityId: Long, @RequestBody city: City) =
        cityRegister.find(cityId).also {
            BeanUtils.copyProperties(city, it, "id")
            cityRegister.save(it)
        }

    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remove(@PathVariable cityId: Long): Unit =
        cityRegister.remove(cityId)

}
