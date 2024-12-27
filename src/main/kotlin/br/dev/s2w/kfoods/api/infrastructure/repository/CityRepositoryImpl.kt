package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.model.City
import br.dev.s2w.kfoods.api.domain.repository.CityRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class CityRepositoryImpl : CityRepository {

    @PersistenceContext
    private lateinit var manager: EntityManager

    override fun list(): List<City> = manager
        .createQuery("from City", City::class.java)
        .resultList

    override fun search(cityId: Long?): City? =
        manager.find(City::class.java, cityId)

    @Transactional
    override fun save(city: City): City {
        return manager.merge(city)
    }

    @Transactional
    override fun remove(cityId: Long) {
        val city = search(cityId)
            ?: throw EmptyResultDataAccessException(1)

        manager.remove(city)
    }

}