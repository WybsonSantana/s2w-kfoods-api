package br.dev.s2w.kfoods.api.domain.service

import br.dev.s2w.kfoods.api.domain.exception.EntityInUseException
import br.dev.s2w.kfoods.api.domain.exception.EntityNotFoundException
import br.dev.s2w.kfoods.api.domain.model.Cuisine
import br.dev.s2w.kfoods.api.domain.repository.CuisineRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class CuisineRegisterService(
    private val cuisineRepository: CuisineRepository
) {

    fun save(cuisine: Cuisine): Cuisine {
        return cuisineRepository.save(cuisine)
    }

    fun remove(cuisineId: Long) {
        try {
            cuisineRepository.remove(cuisineId)
        } catch (e: EmptyResultDataAccessException) {
            throw EntityNotFoundException("There is no cuisine registration with the code $cuisineId")
        } catch (e: DataIntegrityViolationException) {
            throw EntityInUseException("The cuisine with code $cuisineId cannot be removed because it is in use")
        }
    }
}