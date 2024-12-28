package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.model.Restaurant
import br.dev.s2w.kfoods.api.domain.repository.RestaurantRepositoryQueries
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class RestaurantRepositoryImpl : RestaurantRepositoryQueries {

    @PersistenceContext
    private lateinit var manager: EntityManager

    override fun find(name: String, initialFee: BigDecimal, finalFee: BigDecimal): List<Restaurant> {
        val jpql = "from Restaurant where name like :name and deliveryFee between :initialFee and :finalFee"
        return manager.createQuery(jpql, Restaurant::class.java)
            .setParameter("name", "%$name%")
            .setParameter("initialFee", initialFee)
            .setParameter("finalFee", finalFee)
            .resultList
    }

}
