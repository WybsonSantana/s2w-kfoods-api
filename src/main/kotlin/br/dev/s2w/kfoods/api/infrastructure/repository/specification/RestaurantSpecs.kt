package br.dev.s2w.kfoods.api.infrastructure.repository.specification

import br.dev.s2w.kfoods.api.domain.model.Restaurant
import org.springframework.data.jpa.domain.Specification
import java.math.BigDecimal
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

object RestaurantSpecs {

    fun withFreeDelivery(): Specification<Restaurant?> {
        return Specification<Restaurant?> { root: Root<Restaurant?>, query: CriteriaQuery<*>?, builder: CriteriaBuilder ->
            builder.equal(root.get<Any>("deliveryFee"), BigDecimal.ZERO)
        }
    }

    fun withSimilarName(name: String): Specification<Restaurant?> {
        return Specification<Restaurant?> { root: Root<Restaurant?>, query: CriteriaQuery<*>?, builder: CriteriaBuilder ->
            builder.like(root.get("name"), "%$name%")
        }
    }

}
