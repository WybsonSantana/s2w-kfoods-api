package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.Restaurant
import java.math.BigDecimal

interface RestaurantRepositoryQueries {

    fun find(name: String?, initialFee: BigDecimal?, finalFee: BigDecimal?): List<Restaurant>

    fun findWithFreeDelivery(name: String): List<Restaurant>

}
