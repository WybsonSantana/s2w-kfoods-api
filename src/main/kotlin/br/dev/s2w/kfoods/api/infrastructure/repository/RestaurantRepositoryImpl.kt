package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.model.Restaurant
import br.dev.s2w.kfoods.api.domain.repository.RestaurantRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class RestaurantRepositoryImpl : RestaurantRepository {

    @PersistenceContext
    private lateinit var manager: EntityManager

    override fun list(): List<Restaurant> = manager
        .createQuery("from Restaurant", Restaurant::class.java)
        .resultList

    override fun search(id: Long): Restaurant? =
        manager.find(Restaurant::class.java, id)

    @Transactional
    override fun save(restaurant: Restaurant): Restaurant =
        manager.merge(restaurant)

    @Transactional
    override fun remove(restaurant: Restaurant) =
        manager.remove(search(restaurant.id!!))

}