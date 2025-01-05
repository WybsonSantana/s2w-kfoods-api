package br.dev.s2w.kfoods.api.domain.exception

class StateNotFoundException(
    override val message: String?
) : EntityNotFoundException(message) {

    constructor(stateId: Long) : this("There is no state registration with the code $stateId")

}
