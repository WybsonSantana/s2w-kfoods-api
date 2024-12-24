package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.model.Cuisine
import br.dev.s2w.kfoods.api.domain.repository.CuisineRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class CuisineRepositoryImpl : CuisineRepository {

    @PersistenceContext
    private lateinit var manager: EntityManager

    override fun list(): List<Cuisine> = manager
        .createQuery("from Cuisine", Cuisine::class.java)
        .resultList

    override fun search(id: Long?): Cuisine? =
        manager.find(Cuisine::class.java, id)

    @Transactional
    override fun save(cuisine: Cuisine): Cuisine =
        manager.merge(cuisine)

    @Transactional
    override fun remove(cuisineId: Long) {
        val cuisine = search(cuisineId)
            ?: throw EmptyResultDataAccessException(1)

        manager.remove(cuisine)
    }
}
