package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.domain.exception.EntityInUseException
import br.dev.s2w.kfoods.api.domain.exception.EntityNotFoundException
import br.dev.s2w.kfoods.api.domain.model.City
import br.dev.s2w.kfoods.api.domain.repository.CityRepository
import br.dev.s2w.kfoods.api.domain.service.CityRegisterService
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cities")
class CityController(
    private val cityRepository: CityRepository,
    private val cityRegister: CityRegisterService
) {

    @GetMapping
    fun list(): List<City> {
        return cityRepository.list()
    }

    @GetMapping("/{cityId}")
    fun search(@PathVariable cityId: Long?): ResponseEntity<City> {
        val city = cityRepository.search(cityId)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(city)
    }

    @PostMapping
    fun add(@RequestBody city: City): ResponseEntity<Any> {
        try {
            cityRegister.save(city).also {
                return ResponseEntity.status(HttpStatus.CREATED).body(it)
            }
        } catch (e: EntityNotFoundException) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    @PutMapping("/{cityId}")
    fun update(@PathVariable cityId: Long, @RequestBody city: City): ResponseEntity<Any> {
        try {
            val currentCity = cityRepository.search(cityId)
                ?: return ResponseEntity.notFound().build()

            BeanUtils.copyProperties(city, currentCity, "id")

            cityRegister.save(currentCity).also {
                return ResponseEntity.ok(it)
            }
        } catch (e: EntityNotFoundException) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    @DeleteMapping("/{cityId}")
    fun remove(@PathVariable cityId: Long): ResponseEntity<City> {
        try {
            cityRegister.remove(cityId)
            return ResponseEntity.noContent().build()
        } catch (e: EntityNotFoundException) {
            return ResponseEntity.notFound().build()
        } catch (e: EntityInUseException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
    }


}