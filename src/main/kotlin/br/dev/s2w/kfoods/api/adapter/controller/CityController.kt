package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.adapter.exceptionhandler.Problem
import br.dev.s2w.kfoods.api.domain.exception.BusinessException
import br.dev.s2w.kfoods.api.domain.exception.EntityNotFoundException
import br.dev.s2w.kfoods.api.domain.exception.StateNotFoundException
import br.dev.s2w.kfoods.api.domain.model.City
import br.dev.s2w.kfoods.api.domain.repository.CityRepository
import br.dev.s2w.kfoods.api.domain.service.CityRegisterService
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

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
        try {
            cityRegister.save(city)
        } catch (e: StateNotFoundException) {
            throw BusinessException(e.message, e)
        }

    @PutMapping("/{cityId}")
    fun update(@PathVariable cityId: Long, @RequestBody city: City) =
        try {
            cityRegister.find(cityId).also {
                BeanUtils.copyProperties(city, it, "id")

                cityRegister.save(it)
            }
        } catch (e: StateNotFoundException) {
            throw BusinessException(e.message, e)
        }

    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remove(@PathVariable cityId: Long): Unit =
        cityRegister.remove(cityId)

    @ExceptionHandler(BusinessException::class)
    fun handlerBusinessException(e: BusinessException): ResponseEntity<Any> {
        val problem = Problem(
            timestamp = LocalDateTime.now(),
            message = e.message.toString()
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handlerEntityNotFoundException(e: EntityNotFoundException): ResponseEntity<Any> {
        val problem = Problem(
            timestamp = LocalDateTime.now(),
            message = e.message.toString()
        )

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem)
    }

}
