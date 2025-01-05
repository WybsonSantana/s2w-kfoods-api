package br.dev.s2w.kfoods.api.domain.service

import br.dev.s2w.kfoods.api.domain.exception.EntityInUseException
import br.dev.s2w.kfoods.api.domain.exception.EntityNotFoundException
import br.dev.s2w.kfoods.api.domain.model.City
import br.dev.s2w.kfoods.api.domain.repository.CityRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class CityRegisterService(
    private val cityRepository: CityRepository,
    private val stateRegister: StateRegisterService
) {

    private val cityNotFoundMessage = { cityId: Long -> "There is no city registration with the code $cityId" }

    private val cityInUseMessage = { cityId: Long -> "The city with code $cityId cannot be removed because it is in use" }

    fun find(cityId: Long): City {
        return cityRepository.findById(cityId).orElseThrow {
            EntityNotFoundException(cityNotFoundMessage(cityId))
        }
    }

    fun save(city: City): City {
        val stateId = city.state?.id
        val currentState = stateRegister.find(stateId!!)

        city.copy(state = currentState).also {
            return cityRepository.save(it)
        }
    }

    fun remove(cityId: Long) {
        try {
            cityRepository.deleteById(cityId)
        } catch (e: EmptyResultDataAccessException) {
            throw EntityNotFoundException(cityNotFoundMessage(cityId))
        } catch (e: DataIntegrityViolationException) {
            throw EntityInUseException(cityInUseMessage(cityId))
        }
    }

}
