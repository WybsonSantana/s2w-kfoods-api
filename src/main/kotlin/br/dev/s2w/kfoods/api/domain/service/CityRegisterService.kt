package br.dev.s2w.kfoods.api.domain.service

import br.dev.s2w.kfoods.api.domain.exception.EntityInUseException
import br.dev.s2w.kfoods.api.domain.exception.EntityNotFoundException
import br.dev.s2w.kfoods.api.domain.model.City
import br.dev.s2w.kfoods.api.domain.repository.CityRepository
import br.dev.s2w.kfoods.api.domain.repository.StateRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class CityRegisterService(
    private val cityRepository: CityRepository,
    private val stateRepository: StateRepository
) {

    fun save(city: City): City {
        val stateId = city.state?.id
        val currentState = stateRepository.findById(stateId!!).orElse(null)
            ?: throw EntityNotFoundException("There is no state registration with the code $stateId")

        val currentCity = city.copy(state = currentState)

        return cityRepository.save(currentCity)
    }

    fun remove(cityId: Long) {
        try {
            cityRepository.deleteById(cityId)
        } catch (e: EmptyResultDataAccessException) {
            throw EntityNotFoundException("There is no city registration with the code $cityId")
        } catch (e: DataIntegrityViolationException) {
            throw EntityInUseException("The city with code $cityId cannot be removed because it is in use")
        }
    }
}