package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.model.Cidade
import br.dev.s2w.kfoods.api.domain.repository.CidadeRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class CidadeRepositoryImpl(
    @PersistenceContext private val manager: EntityManager
) : CidadeRepository {

    override fun listar(): List<Cidade> {
        return manager.createQuery("from Cidade", Cidade::class.java).resultList
    }

    override fun buscar(cidadeId: Long): Cidade? {
        return manager.find(Cidade::class.java, cidadeId)
    }

    @Transactional
    override fun salvar(cidade: Cidade): Cidade {
        return manager.merge(cidade)
    }

    @Transactional
    override fun remover(cidadeId: Long) {
        val cidade = buscar(cidadeId) ?: throw EmptyResultDataAccessException(1)
        manager.remove(cidade)
    }

}