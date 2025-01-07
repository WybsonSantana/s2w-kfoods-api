package br.dev.s2w.kfoods.api.domain.exception

class CuisineNotFoundException(
    override val message: String?
) : EntityNotFoundException(message) {

    constructor(cuisineId: Long) : this("There is no cuisine registration with the code $cuisineId")

}
