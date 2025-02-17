package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.domain.model.State
import br.dev.s2w.kfoods.api.domain.repository.StateRepository
import br.dev.s2w.kfoods.api.domain.service.StateRegisterService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/states")
class StateController(
    private val stateRepository: StateRepository,
    private val stateRegister: StateRegisterService
) {

    @GetMapping
    fun list(): List<State> =
        stateRepository.findAll()

    @GetMapping("/{stateId}")
    fun find(@PathVariable stateId: Long): State =
        stateRegister.find(stateId)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun add(@RequestBody @Valid state: State): State =
        stateRegister.save(state)

    @PutMapping("/{stateId}")
    fun update(@PathVariable stateId: Long, @RequestBody @Valid state: State): State =
        stateRegister.find(stateId).also { currentState ->
            currentState.copy(
                name = state.name
            ).let { updatedState ->
                stateRegister.save(updatedState)
            }
        }

    @DeleteMapping("/{stateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remove(@PathVariable stateId: Long): Unit =
        stateRegister.remove(stateId)

}
