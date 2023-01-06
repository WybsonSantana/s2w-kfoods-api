package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.model.Cozinha
import br.dev.s2w.kfoods.api.domain.repository.CozinhaRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class CozinhaRepositoryImpl(
    @PersistenceContext private val manager: EntityManager
) : CozinhaRepository {

    override fun listar(): List<Cozinha> {
        return manager.createQuery("from Cozinha", Cozinha::class.java).resultList
    }

    override fun buscar(id: Long): Cozinha {
        return manager.find(Cozinha::class.java, id)
    }

    @Transactional
    override fun salvar(cozinha: Cozinha): Cozinha {
        return manager.merge(cozinha)
    }

    @Transactional
    override fun remover(cozinha: Cozinha) {
        manager.remove(buscar(cozinha.id))
    }

}