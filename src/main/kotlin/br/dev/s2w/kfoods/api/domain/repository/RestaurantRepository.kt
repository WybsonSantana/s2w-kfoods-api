package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.Restaurant
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
interface RestaurantRepository : CustomJpaRepository<Restaurant, Long>,
    RestaurantRepositoryQueries, JpaSpecificationExecutor<Restaurant> {

    @Query("from Restaurant restaurant join fetch restaurant.cuisine")
    override fun findAll(): List<Restaurant>

    fun findByDeliveryFeeBetween(initialFee: BigDecimal, finalFee: BigDecimal): List<Restaurant>

    //@Query("from Restaurant where name like %:name% and cuisine.id = :cuisineId")
    fun queryByName(name: String, cuisineId: Long): List<Restaurant>

    //fun findByNameContainingAndCuisineId(name: String, cuisineId: Long): List<Restaurant>

    fun getFirstRestaurantByNameContaining(name: String): Restaurant?

    fun queryTop2ByNameContaining(name: String): List<Restaurant>

    fun countByCuisineId(cuisineId: Long): Int

}
