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

    private val cuisineNotFoundMessage = { cuisineId: Long -> "There is no cuisine registration with the code $cuisineId" }

    private val cuisineInUseMessage = { cuisineId: Long -> "The cuisine with code $cuisineId cannot be removed because it is in use" }

    fun find(cuisineId: Long): Cuisine {
        return cuisineRepository.findById(cuisineId)
            .orElseThrow {
                EntityNotFoundException(cuisineNotFoundMessage(cuisineId))
            }
    }

    fun save(cuisine: Cuisine): Cuisine {
        return cuisineRepository.save(cuisine)
    }

    fun remove(cuisineId: Long) {
        try {
            cuisineRepository.deleteById(cuisineId)
        } catch (e: EmptyResultDataAccessException) {
            throw EntityNotFoundException(cuisineNotFoundMessage(cuisineId))
        } catch (e: DataIntegrityViolationException) {
            throw EntityInUseException(cuisineInUseMessage(cuisineId))
        }
    }

}
