package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.model.Estado
import br.dev.s2w.kfoods.api.domain.repository.EstadoRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class EstadoRepositoryImpl(
    @PersistenceContext private val manager: EntityManager
) : EstadoRepository {

    override fun listar(): List<Estado> {
        return manager.createQuery("from Estado", Estado::class.java).resultList
    }

    override fun buscar(estadoId: Long): Estado? {
        return manager.find(Estado::class.java, estadoId)
    }

    @Transactional
    override fun salvar(estado: Estado): Estado {
        return manager.merge(estado)
    }

    @Transactional
    override fun remover(estadoId: Long) {
        val estado = buscar(estadoId) ?: throw EmptyResultDataAccessException(1)
        manager.remove(estado)
    }

}