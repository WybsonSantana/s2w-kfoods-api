package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.model.City
import br.dev.s2w.kfoods.api.domain.repository.CityRepository
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

    override fun search(id: Long): City =
        manager.find(City::class.java, id)

    @Transactional
    override fun save(city: City): City =
        manager.merge(city)

    @Transactional
    override fun remove(city: City) =
        manager.remove(search(city.id!!))

}