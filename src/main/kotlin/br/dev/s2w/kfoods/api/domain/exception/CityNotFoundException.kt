package br.dev.s2w.kfoods.api.domain.exception

class CityNotFoundException(
    override val message: String?
) : EntityNotFoundException(message) {

    constructor(cityId: Long) : this("There is no city registration with the code $cityId")

}
