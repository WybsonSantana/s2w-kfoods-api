package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.domain.exception.EntityInUseException
import br.dev.s2w.kfoods.api.domain.exception.EntityNotFoundException
import br.dev.s2w.kfoods.api.domain.model.State
import br.dev.s2w.kfoods.api.domain.repository.StateRepository
import br.dev.s2w.kfoods.api.domain.service.StateRegisterService
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/states")
class StateController(
    private val stateRepository: StateRepository,
    private val stateRegister: StateRegisterService
) {

    @GetMapping
    fun list(): List<State> {
        return stateRepository.list()
    }

    @GetMapping("/{stateId}")
    fun search(@PathVariable stateId: Long): ResponseEntity<State> {
        val state = stateRepository.search(stateId)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(state)
    }

    @PostMapping
    fun add(@RequestBody state: State): ResponseEntity<State> {
        stateRegister.save(state).also {
            return ResponseEntity.ok(it)
        }
    }

    @PutMapping("/{stateId}")
    fun update(@PathVariable stateId: Long, @RequestBody state: State): ResponseEntity<State> {
        val currentState = stateRepository.search(stateId)
            ?: return ResponseEntity.notFound().build()

        BeanUtils.copyProperties(state, currentState, "id")

        stateRegister.save(currentState).also {
            return ResponseEntity.ok(it)
        }
    }

    @DeleteMapping("/{stateId}")
    fun remove(@PathVariable stateId: Long): ResponseEntity<Any> {
        try {
            stateRegister.remove(stateId)
            return ResponseEntity.noContent().build()
        } catch (e: EntityNotFoundException) {
            return ResponseEntity.notFound().build()
        } catch (e: EntityInUseException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.message)
        }
    }

}
