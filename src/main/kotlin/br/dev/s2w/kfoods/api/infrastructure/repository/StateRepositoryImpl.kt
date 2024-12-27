package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.model.State
import br.dev.s2w.kfoods.api.domain.repository.StateRepository
import org.springframework.dao.EmptyResultDataAccessException
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

    override fun search(stateId: Long?): State? =
        manager.find(State::class.java, stateId)

    @Transactional
    override fun save(state: State): State =
        manager.merge(state)

    @Transactional
    override fun remove(stateId: Long) {
        val state = search(stateId)
            ?: throw EmptyResultDataAccessException(1)

        manager.remove(state)
    }

}
