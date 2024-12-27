package br.dev.s2w.kfoods.api.domain.service

import br.dev.s2w.kfoods.api.domain.exception.EntityInUseException
import br.dev.s2w.kfoods.api.domain.exception.EntityNotFoundException
import br.dev.s2w.kfoods.api.domain.model.State
import br.dev.s2w.kfoods.api.domain.repository.StateRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class StateRegisterService(
    private val stateRepository: StateRepository
) {

    fun save(state: State): State =
        stateRepository.save(state)

    fun remove(stateId: Long) {
        try {
            stateRepository.remove(stateId)
        } catch (e: EmptyResultDataAccessException) {
            throw EntityNotFoundException("There is no state registration with the code $stateId")
        } catch (e: DataIntegrityViolationException) {
            throw EntityInUseException("The state with code $stateId cannot be removed because it is in use")
        }
    }

}
