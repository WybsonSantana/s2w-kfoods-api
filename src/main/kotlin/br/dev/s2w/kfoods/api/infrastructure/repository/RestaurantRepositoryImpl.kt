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

    override fun find(name: String?, initialFee: BigDecimal?, finalFee: BigDecimal?): List<Restaurant> {
        val builder = manager.criteriaBuilder
        val criteria = builder.createQuery(Restaurant::class.java)
        val root = criteria.from(Restaurant::class.java)

        val namePredicate = builder.like(root.get("name"), "%$name%")
        val initialFeePredicate = builder.greaterThanOrEqualTo(root.get("deliveryFee"), initialFee)
        val finalFeePredicate = builder.lessThanOrEqualTo(root.get("deliveryFee"), finalFee)

        criteria.where(namePredicate, initialFeePredicate, finalFeePredicate)

        manager.createQuery(criteria).also {
            return it.resultList
        }
    }

}
