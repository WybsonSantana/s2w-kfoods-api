package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.model.State
import br.dev.s2w.kfoods.api.domain.repository.StateRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class StateRepositoryImpl : StateRepository {

    @PersistenceContext
    private lateinit var manager: EntityManager

    override fun list(): List<State> = manager
        .createQuery("from State", State::class.java)
        .resultList

    override fun search(id: Long): State =
        manager.find(State::class.java, id)

    @Transactional
    override fun save(state: State): State =
        manager.merge(state)

    @Transactional
    override fun remove(state: State) =
        manager.remove(search(state.id!!))

}