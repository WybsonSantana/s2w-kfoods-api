package br.dev.s2w.kfoods.api.domain.service

import br.dev.s2w.kfoods.api.domain.exception.CuisineNotFoundException
import br.dev.s2w.kfoods.api.domain.exception.EntityInUseException
import br.dev.s2w.kfoods.api.domain.model.Cuisine
import br.dev.s2w.kfoods.api.domain.repository.CuisineRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CuisineRegisterService(
    private val cuisineRepository: CuisineRepository
) {

    private val cuisineInUseMessage = { cuisineId: Long -> "The cuisine with code $cuisineId cannot be removed because it is in use" }

    fun find(cuisineId: Long): Cuisine {
        return cuisineRepository.findById(cuisineId).orElseThrow {
            CuisineNotFoundException(cuisineId)
        }
    }

    @Transactional
    fun save(cuisine: Cuisine): Cuisine {
        return cuisineRepository.save(cuisine)
    }

    @Transactional
    fun remove(cuisineId: Long) {
        try {
            cuisineRepository.deleteById(cuisineId)
            cuisineRepository.flush()
        } catch (e: EmptyResultDataAccessException) {
            throw CuisineNotFoundException(cuisineId)
        } catch (e: DataIntegrityViolationException) {
            throw EntityInUseException(cuisineInUseMessage(cuisineId))
        }
    }

}
