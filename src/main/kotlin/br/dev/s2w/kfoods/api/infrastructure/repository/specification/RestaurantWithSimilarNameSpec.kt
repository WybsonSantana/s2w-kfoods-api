package br.dev.s2w.kfoods.api.infrastructure.repository.specification

import br.dev.s2w.kfoods.api.domain.model.Restaurant
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class RestaurantWithSimilarNameSpec(
    private val name: String
) : Specification<Restaurant> {

    override fun toPredicate(
        root: Root<Restaurant>,
        query: CriteriaQuery<*>,
        builder: CriteriaBuilder
    ): Predicate? {
        return builder.like(root.get("name"), "%$name%")
    }
}