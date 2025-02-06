package br.dev.s2w.kfoods.api.domain.service

import br.dev.s2w.kfoods.api.domain.exception.CityNotFoundException
import br.dev.s2w.kfoods.api.domain.exception.EntityInUseException
import br.dev.s2w.kfoods.api.domain.model.City
import br.dev.s2w.kfoods.api.domain.repository.CityRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CityRegisterService(
    private val cityRepository: CityRepository,
    private val stateRegister: StateRegisterService
) {

    private val cityInUseMessage = { cityId: Long -> "The city with code $cityId cannot be removed because it is in use" }

    fun find(cityId: Long): City {
        return cityRepository.findById(cityId).orElseThrow {
            CityNotFoundException(cityId)
        }
    }

    @Transactional
    fun save(city: City): City {
        val stateId = city.state?.id
        val currentState = stateRegister.find(stateId!!)

        city.copy(state = currentState).also {
            return cityRepository.save(it)
        }
    }

    @Transactional
    fun remove(cityId: Long) {
        try {
            cityRepository.deleteById(cityId)
        } catch (e: EmptyResultDataAccessException) {
            throw CityNotFoundException(cityId)
        } catch (e: DataIntegrityViolationException) {
            throw EntityInUseException(cityInUseMessage(cityId))
        }
    }

}
