package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.State

interface StateRepository {
    fun list(): List<State>
    fun search(stateId: Long?): State?
    fun save(state: State): State
    fun remove(stateId: Long)
}