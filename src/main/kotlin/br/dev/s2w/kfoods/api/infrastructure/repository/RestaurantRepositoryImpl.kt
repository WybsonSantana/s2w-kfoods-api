package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.model.Restaurant
import br.dev.s2w.kfoods.api.domain.repository.RestaurantRepositoryQueries
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils
import java.math.BigDecimal
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class RestaurantRepositoryImpl : RestaurantRepositoryQueries {

    @PersistenceContext
    private lateinit var manager: EntityManager

    override fun find(name: String?, initialFee: BigDecimal?, finalFee: BigDecimal?): List<Restaurant> {
        val jpql = StringBuilder()
        val parameters = HashMap<String, Any>()

        jpql.append("from Restaurant where 0 = 0 ")

        if (StringUtils.hasLength(name)) {
            jpql.append("and name like :name ")
            parameters["name"] = "%$name%"
        }

        if (initialFee != null) {
            jpql.append("and deliveryFee >= :initialFee ")
            parameters["initialFee"] = initialFee
        }

        if (finalFee != null) {
            jpql.append("and deliveryFee <= :finalFee ")
            parameters["finalFee"] = finalFee
        }

        val query = manager.createQuery(jpql.toString(), Restaurant::class.java)

        parameters.forEach { (key: String?, value: Any?) -> query.setParameter(key, value) }

        return query.resultList
    }

}
