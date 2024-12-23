package br.dev.s2w.kfoods.api.domain.service

import br.dev.s2w.kfoods.api.domain.model.Cuisine
import br.dev.s2w.kfoods.api.domain.repository.CuisineRepository
import org.springframework.stereotype.Service

@Service
class CuisineRegisterService(
    private val cuisineRepository: CuisineRepository
) {

    fun save(cuisine: Cuisine): Cuisine =
        cuisineRepository.save(cuisine)

}