package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.model.Restaurant
import br.dev.s2w.kfoods.api.domain.repository.RestaurantRepositoryQueries
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils
import java.math.BigDecimal
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.Predicate

@Repository
class RestaurantRepositoryImpl : RestaurantRepositoryQueries {

    @PersistenceContext
    private lateinit var manager: EntityManager

    override fun find(name: String?, initialFee: BigDecimal?, finalFee: BigDecimal?): List<Restaurant> {
        val builder = manager.criteriaBuilder
        val criteria = builder.createQuery(Restaurant::class.java)
        val root = criteria.from(Restaurant::class.java)

        val predicates = ArrayList<Predicate>()

        if (StringUtils.hasText(name))
            predicates.add(builder.like(root.get("name"), "%$name%"))

        if (initialFee != null)
            predicates.add(builder.greaterThanOrEqualTo(root.get("deliveryFee"), initialFee))

        if (finalFee != null)
            predicates.add(builder.lessThanOrEqualTo(root.get("deliveryFee"), finalFee))

        criteria.where(*predicates.toTypedArray<Predicate>())

        manager.createQuery(criteria).also {
            return it.resultList
        }
    }

}