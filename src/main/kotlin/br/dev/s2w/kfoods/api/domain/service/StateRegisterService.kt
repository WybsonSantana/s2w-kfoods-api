package br.dev.s2w.kfoods.api.domain.service

import br.dev.s2w.kfoods.api.domain.exception.EntityInUseException
import br.dev.s2w.kfoods.api.domain.exception.StateNotFoundException
import br.dev.s2w.kfoods.api.domain.model.State
import br.dev.s2w.kfoods.api.domain.repository.StateRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class StateRegisterService(
    private val stateRepository: StateRepository
) {

    private val stateInUseMessage = { stateId: Long -> "The state with code $stateId cannot be removed because it is in use" }

    fun find(stateId: Long): State {
        return stateRepository.findById(stateId).orElseThrow {
            StateNotFoundException(stateId)
        }
    }

    fun save(state: State): State {
        return stateRepository.save(state)
    }

    fun remove(stateId: Long) {
        try {
            stateRepository.deleteById(stateId)
        } catch (e: EmptyResultDataAccessException) {
            throw StateNotFoundException(stateId)
        } catch (e: DataIntegrityViolationException) {
            throw EntityInUseException(stateInUseMessage(stateId))
        }
    }

}
