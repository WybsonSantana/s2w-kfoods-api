package br.dev.s2w.kfoods.api.domain.exception

class RestaurantNotFoundException(
    override val message: String?
) : EntityNotFoundException(message) {

    constructor(restaurantId: Long) : this("There is no restaurant registration with the code $restaurantId")

}
